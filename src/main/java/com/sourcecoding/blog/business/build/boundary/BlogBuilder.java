/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.boundary;

import com.sourcecoding.blog.business.build.control.ContentBuilder;
import com.sourcecoding.blog.business.build.control.ContentCollector;
import com.sourcecoding.blog.business.build.control.CopyingMachine;
import com.sourcecoding.blog.business.build.entity.BlogEntry;
import com.sourcecoding.blog.business.configuration.boundary.ConfigService;
import com.sourcecoding.blog.business.configuration.entity.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author mre
 */
@Stateless
@Path("blogbuilder")
public class BlogBuilder {

    @Inject
    ConfigService configService;

    @PUT
    public void runBuilder() throws Exception {

        Configuration config = configService.getConfiguration();

        ContentCollector cc = new ContentCollector(config);
        List<BlogEntry> entries = cc.collect();

        ContentBuilder cb = new ContentBuilder(entries, config);

        //Freemarker configuration object
        freemarker.template.Configuration templateConfiguration = new freemarker.template.Configuration();
        templateConfiguration.setDirectoryForTemplateLoading(new File(config.getFreemarkerTemplateDirectoryPath()));
        Template template = templateConfiguration.getTemplate("article.html");
        cb.createBlogEntries(template);

        template = templateConfiguration.getTemplate("index.html");
        cb.createIndex(template, 5);

        CopyingMachine cp = new CopyingMachine();
        cp.copyDirectory(config.getWebResourcesDirctoryPath(), config.getHtmlExportRootDirectoryPath());
    }

    @GET
    public List<BlogEntry> getCurrentBloggerContent() {
        Configuration config = configService.getConfiguration();

        ContentCollector cc = new ContentCollector(config);
        List<BlogEntry> entries = cc.collect();

        return entries;
    }
}
