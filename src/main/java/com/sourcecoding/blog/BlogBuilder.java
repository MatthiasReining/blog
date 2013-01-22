/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.model.BlogEntry;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mre
 */
public class BlogBuilder {

    static String exportPath;
     Map<String, Object> data = new HashMap<>();

    //Freemarker configuration object
    Configuration cfg = new Configuration();

    public static void main(String... args) throws Exception {
        if (args.length > 0) {
            exportPath = args[0];
        } else {
            exportPath = "D:\\java-server\\tomcat\\apache-tomcat-7.0.34\\webapps\\blog";
        }

        new BlogBuilder().run();
    }

    void run() throws Exception {
        List<BlogEntry> entries = ContentCollector.collect();

        data.put("blogRoot","/blog");
        createBlogEntries(entries);
        createIndex(entries);
    }

    void createBlogEntries(List<BlogEntry> entries) throws Exception {
        Template template = cfg.getTemplate("src/main/resources/templates/article.html");


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

    void createIndex(List<BlogEntry> entries) throws Exception {
        Template template = cfg.getTemplate("src/main/resources/templates/index.html");

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
