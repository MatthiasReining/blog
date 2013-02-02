/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.business.build.control.*;
import com.sourcecoding.blog.business.build.entity.BlogEntry;
import com.sourcecoding.blog.business.configuration.entity.Configuration;
import freemarker.template.Template;
import java.util.List;

/**
 *
 * @author mre
 */
public class BlogBuilder {

    public static void main(String... args) throws Exception {
        String exportPath;

        if (args.length > 0)
            exportPath = args[0];
        else
            exportPath = "D:\\java-server\\tomcat\\apache-tomcat-7.0.34\\webapps\\blog";

        Configuration config = new Configuration();
        config.setBlogPath("/blog");
        config.setFreemarkerTemplateDirectoryPath("D:\\labs\\my-blog\\resources\\templates");
        config.setHtmlExportRootDirectoryPath(exportPath);
        config.setMarkdownContentDirectoryPath("D:\\labs\\my-blog\\resources\\content");
        config.setWebResourcesDirctoryPath("D:\\labs\\my-blog\\resources\\web-resources");


        ContentCollector cc = new ContentCollector(config);
        List<BlogEntry> entries = cc.collect();

        ContentBuilder cb = new ContentBuilder(entries, config);

        //Freemarker configuration object
        freemarker.template.Configuration templateConfiguration = new freemarker.template.Configuration();
        Template template = templateConfiguration.getTemplate("src/main/resources/templates/article.html");
        cb.createBlogEntries(template);

        template = templateConfiguration.getTemplate("src/main/resources/templates/index.html");
        cb.createIndex(template, 5);

        CopyingMachine cp = new CopyingMachine();
        cp.copyDirectory(config.getWebResourcesDirctoryPath(), config.getHtmlExportRootDirectoryPath());
    }
}
