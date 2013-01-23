/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.configuration.boundary.entity;

/**
 *
 * @author mre
 */
public class Configuration {

    private String htmlExportRootDirectoryPath;
    private String markdownContentDirectoryPath;
    private String blogPath;
    private String freemarkerTemplateDirectoryPath;
    private String webResourcesDirctoryPath;

    public String getHtmlExportRootDirectoryPath() {
        return htmlExportRootDirectoryPath;
    }

    /**
     * Path to apache http server
     * <code>www</code> directory. For example
     * <code>/var/www/html</code> (bad example ;-) )
     *
     * @param htmlExportRootDirectoryPath
     */
    public void setHtmlExportRootDirectoryPath(String htmlExportRootDirectoryPath) {
        this.htmlExportRootDirectoryPath = htmlExportRootDirectoryPath;
    }

    public String getMarkdownContentDirectoryPath() {
        return markdownContentDirectoryPath;
    }

    /**
     * Path to directory containing all MarkDown blog articles.
     *
     * @param markdownContentDirectoryPath
     */
    public void setMarkdownContentDirectoryPath(String markdownContentDirectoryPath) {
        this.markdownContentDirectoryPath = markdownContentDirectoryPath;
    }

    public String getBlogPath() {
        return blogPath;
    }

    /**
     * URL-Path to blog site.
     * <p>
     * for example: <br/>
     * <code>/blog</code> (if the complete url is http://myserver.com/blog)<br/>
     * or<br/>
     * <code>/</code> (if the complete url is http://blog.myserver.com)<br/>
     * </p>
     *
     * @param blogPath
     */
    public void setBlogPath(String blogPath) {
        this.blogPath = blogPath;
    }

    public String getFreemarkerTemplateDirectoryPath() {
        return freemarkerTemplateDirectoryPath;
    }

    /**
     * Path to directory containing all Freemarker templates.
     *
     * @param freemarkerTemplateDirectoryPath
     */
    public void setFreemarkerTemplateDirectoryPath(String freemarkerTemplateDirectoryPath) {
        this.freemarkerTemplateDirectoryPath = freemarkerTemplateDirectoryPath;
    }

    public String getWebResourcesDirctoryPath() {
        return webResourcesDirctoryPath;
    }

    /**
     * Path to directory containing all web site resources (CSS-files, images,
     * ...).
     *
     * @param webResourcesDirctoryPath
     */
    public void setWebResourcesDirctoryPath(String webResourcesDirctoryPath) {
        this.webResourcesDirctoryPath = webResourcesDirctoryPath;
    }
}
