/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.business.build.control.*;
import com.sourcecoding.blog.business.build.entity.BlogEntry;
import com.sourcecoding.blog.business.configuration.entity.Configuration;
import freemarker.template.Template;
import java.io.File;
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

        String myBlogRootPath = "D:\\labs\\my-blog\\";

        Configuration config = new Configuration();
        config.setBlogPath("/blog");
        config.setFreemarkerTemplateDirectoryPath(myBlogRootPath + "resources\\templates");
        config.setHtmlExportRootDirectoryPath(exportPath);
        config.setMarkdownContentDirectoryPath(myBlogRootPath + "resources\\content");
        config.setWebResourcesDirctoryPath(myBlogRootPath + "resources\\web-resources");


        ContentCollector cc = new ContentCollector(config);
        List<BlogEntry> entries = cc.collect();

        ContentBuilder cb = new ContentBuilder(entries, config);

        //Freemarker configuration object

        //Freemarker configuration object
        freemarker.template.Configuration templateConfiguration = new freemarker.template.Configuration();
        templateConfiguration.setDirectoryForTemplateLoading(new File(config.getFreemarkerTemplateDirectoryPath()));
        Template template = templateConfiguration.getTemplate("article.html");
        cb.createBlogEntries(template);

        template = templateConfiguration.getTemplate("index.html");
        cb.createIndex(template, 5);

        template = templateConfiguration.getTemplate("sitemap.xml");
        cb.createSiteMapXML(template);

        template = templateConfiguration.getTemplate("rss2.xml");
        cb.createRSS(template);

        CopyingMachine cp = new CopyingMachine();
        cp.copyDirectory(config.getWebResourcesDirctoryPath(), config.getHtmlExportRootDirectoryPath());


    }
}
