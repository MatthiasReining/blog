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
        config.setFreemarkerTemplateDirectoryPath("/data/blog/blog/src/main/resources/templates");
        config.setHtmlExportRootDirectoryPath("/data/apache/blog.matthias-reining.com");
        config.setMarkdownContentDirectoryPath("/data/blog/blog/src/test/resources");
        config.setWebResourcesDirctoryPath("/data/blog/blog/src/main/resources/web-resources");
        config.setScmCheckoutDirectoryPath("/data/blog/blog");

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
