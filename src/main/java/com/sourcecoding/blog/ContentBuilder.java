/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 *
 * @author mre
 */
public class ContentBuilder {

    private String exportPath;
    Map<String, Object> data = new HashMap<>();
    //Freemarker configuration object
    Configuration cfg = new Configuration();

    public ContentBuilder(String exportPath, String blogRoot) {

        data.put("blogRoot", "/blog");

        this.exportPath = exportPath;
    }

    public void createBlogEntries(List<BlogEntry> entries, Template template) throws Exception {

        data.put("isIndexPage", false);
        for (BlogEntry entry : entries) {

            data.put("entry", entry);
            // Console output
            //Writer out = new OutputStreamWriter(System.out);
            //template.process(data, out);
            //out.flush();
            String articleDirPath = exportPath + File.separator + entry.getKey();

            new File(articleDirPath).mkdir();

            String filePath = articleDirPath + File.separator + "index.html";
            System.out.println("create " + filePath);
            try (Writer file = new FileWriter(new File(filePath))) {
                template.process(data, file);
                file.flush();
            }

        }
    }

    void createIndex(List<BlogEntry> entries, Template template) throws Exception {

        // Build the data-model
        BlogEntry latestEntry = entries.get(0);
        data.put("entry", latestEntry);
        data.put("entries", entries);
        data.put("isIndexPage", true);


        // Console output
        //Writer out = new OutputStreamWriter(System.out);
        //template.process(data, out);
        //out.flush();

        String filePath = exportPath + File.separator + "index.html";
        System.out.println("create " + filePath);
        try (Writer file = new FileWriter(new File(filePath))) {
            template.process(data, file);
            file.flush();
        }
    }
}
