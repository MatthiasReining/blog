/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import freemarker.template.Configuration;
import freemarker.template.Template;
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
        if (args.length > 0)
            exportPath = args[0];
        else
            exportPath = "D:\\java-server\\tomcat\\apache-tomcat-7.0.34\\webapps\\blog";

        new BlogBuilder().run();
    }

    void run() throws Exception {
        ContentCollector cc = new ContentCollector("D:\\labs\\blog\\src\\test\\resources");
        List<BlogEntry> entries = cc.collect();

        ContentBuilder cb = new ContentBuilder(exportPath, "/blog");

        Template template = cfg.getTemplate("src/main/resources/templates/article.html");
        cb.createBlogEntries(entries, template);

        template = cfg.getTemplate("src/main/resources/templates/index.html");
        cb.createIndex(entries, template);
    }
}
