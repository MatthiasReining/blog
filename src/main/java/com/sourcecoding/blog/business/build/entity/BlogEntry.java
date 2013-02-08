/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.entity;

import freemarker.template.utility.StringUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mre
 */
public class BlogEntry implements Serializable, Comparable<BlogEntry> {

    private String title;
    private String subtitle;
    private Date created;
    private String htmlContent;
    private List<String> tags;
    private String key;
    private BlogEntry nextEntry;
    private BlogEntry prevEntry;

    public String getAbstractHTMLContent() {
        return htmlContent.substring(0, htmlContent.indexOf("</p>"));
    }

    public String getAbstractHtmlEncodedContent() {
        return StringUtil.HTMLEnc(getAbstractHTMLContent());
    }

    public String getHtmlEncodedContent() {
        return StringUtil.HTMLEnc(htmlContent);
    }

    public String getRssContent() {
        return "<![CDATA[" + htmlContent + "]]>";
    }

    public String getKey() {
        if (key == null)
            key = BlogEntry.createSEOFriendlyURL(title);
        return key;
    }

    static String createSEOFriendlyURL(String input) {
        input = input.toLowerCase();
        input = input.replaceAll("ß", "ss");
        input = input.replaceAll("ü", "ue");
        input = input.replaceAll("ö", "oe");
        input = input.replaceAll("ä", "ae");
        input = input.replaceAll("€", "eur");
        input = input.replaceAll("@", "at");
        input = input.replaceAll("[^a-z\\s]", "").replaceAll("\\s", "-");
        return input;
    }

    @Override
    public int compareTo(BlogEntry o) {

        if (o == null || o.getCreated() == null)
            return -1;
        if (this.getCreated() == null)
            return 1;
        return (o.getCreated().compareTo(this.getCreated()));

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.key = BlogEntry.createSEOFriendlyURL(title);
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public BlogEntry getNextEntry() {
        return nextEntry;
    }

    public void setNextEntry(BlogEntry nextEntry) {
        this.nextEntry = nextEntry;
    }

    public BlogEntry getPrevEntry() {
        return prevEntry;
    }

    public void setPrevEntry(BlogEntry prevEntry) {
        this.prevEntry = prevEntry;
    }
}
