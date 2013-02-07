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
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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
        templateConfiguration.setDefaultEncoding("UTF-8");
        templateConfiguration.setLocale(Locale.US);
        templateConfiguration.setTimeZone(TimeZone.getTimeZone("GMT"));
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

        //FIXME call sitemap registration
        //<searchengine_URL>/ping?sitemap=http://www.example.com/sitemap.gz
    }

    @GET
    public List<BlogEntry> getCurrentBloggerContent() throws IOException {
        Configuration config = configService.getConfiguration();

        ContentCollector cc = new ContentCollector(config);
        List<BlogEntry> entries = cc.collect();

        return entries;
    }

    @Path("fromGitHub")
    @POST
    public void runBuilderFromGitHub() throws Exception {
        runBuilder();
    }
}
