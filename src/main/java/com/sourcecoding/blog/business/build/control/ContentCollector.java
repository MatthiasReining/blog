/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import com.sourcecoding.blog.business.build.entity.BlogEntry;
import com.sourcecoding.blog.business.configuration.boundary.entity.Configuration;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.pegdown.PegDownProcessor;

/**
 *
 * @author mre
 */
public class ContentCollector {

    private String mdContentDirectoryPath;

    public ContentCollector(Configuration config) {
        this.mdContentDirectoryPath = config.getMarkdownContentDirectoryPath();
    }

    private File[] getMDBlogEntries() {
        File mdDirectory = new File(mdContentDirectoryPath);
        File[] mdFiles = mdDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".md"))
                    return true;
                return false;
            }
        });

        return mdFiles;
    }

    public List<BlogEntry> collect() {
        List<BlogEntry> entries = new ArrayList<>();
        for (File mdFile : getMDBlogEntries()) {
            try {
                BlogEntry be = createBlogEntry(mdFile);

                entries.add(be);

            } catch (IOException | ParseException e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
        return entries;
    }

    private BlogEntry createBlogEntry(File mdFile) throws FileNotFoundException, IOException, ParseException {

        BlogEntry be = new BlogEntry();

        boolean collectMetaInfo = true;
        StringBuilder content = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(mdFile));

        while ((line = br.readLine()) != null) {

            if (line.isEmpty()) //after an empty line markdown content starts
                collectMetaInfo = false;

            if (collectMetaInfo)
                createMetaInformation(line, be);
            else
                content.append(line).append("\n");
        }

        PegDownProcessor pdp = new PegDownProcessor();
        String htmlContent = pdp.markdownToHtml(content.toString());
        be.setHtmlContent(htmlContent);

        return be;
    }

    private String getValueFromPropertyLine(String line, String key) {
        if (line.startsWith(key)) {
            String value = new String(line.substring(key.length() + 1));
            return value;
        }
        return null;
    }

    private void createMetaInformation(String line, BlogEntry be) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        if (line.startsWith("title"))
            be.setTitle(getValueFromPropertyLine(line, "title"));

        if (line.startsWith("subtitle"))
            be.setSubtitle(getValueFromPropertyLine(line, "subtitle"));

        if (line.startsWith("created")) {
            String dateText = getValueFromPropertyLine(line, "created");
            be.setCreated(sdf.parse(dateText));
        }
    }
}
