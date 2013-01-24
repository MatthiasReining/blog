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
        config.setBlogPath("/blog");
        config.setFreemarkerTemplateDirectoryPath("D:\\labs\\blog\\src\\main\\resources\\templates");
        config.setHtmlExportRootDirectoryPath("D:\\java-server\\tomcat\\apache-tomcat-7.0.34\\webapps\\blog");
        config.setMarkdownContentDirectoryPath("D:\\labs\\blog\\src\\test\\resources");
        config.setWebResourcesDirctoryPath("D:\\labs\\blog\\src\\main\\resources\\web-resources");


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
