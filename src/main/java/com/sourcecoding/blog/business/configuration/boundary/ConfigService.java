/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.configuration.boundary;

import com.sourcecoding.blog.business.configuration.entity.Configuration;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mre
 */
@Singleton
@Path("config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigService {

    Configuration config;

    @PostConstruct
    void init() {

        config = new Configuration();
        config.setBlogPath("");
        config.setHostname("http://blog.matthias-reining.com");
        config.setFreemarkerTemplateDirectoryPath("/data/blog/my-blog/resources/templates");
        config.setHtmlExportRootDirectoryPath("/data/httpd/blog.matthias-reining.com");
        config.setMarkdownContentDirectoryPath("/data/blog/my-blog/resources/content");
        config.setWebResourcesDirctoryPath("/data/blog/my-blog/resources/web-resources");
        config.setScmCheckoutDirectoryPath("/data/blog/my-blog");

    }

    @GET
    public Configuration getConfiguration() {
        return config;
    }

    @POST
    public Configuration updateConfiguration(Configuration config) {
        this.config = config;
        return config;
    }
}
