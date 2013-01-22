/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.presentation;

import com.sourcecoding.blog.model.BlogEntry;
import com.sourcecoding.blog.EntryProcessor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mre
 */
@Named
@RequestScoped
public class Index {
    
    @Inject
    EntryProcessor ep;
    
    @PostConstruct
    void create() {
        latestEntry = ep.getEntries().get(0);
        lastEntries = ep.getEntries().subList(1, ep.getEntries().size());        
    }
    
    private BlogEntry latestEntry;
    
    private List<BlogEntry> lastEntries;

    public List<BlogEntry> getLastEntries() {
        return lastEntries;
    }

    public void setLastEntries(List<BlogEntry> lastEntries) {
        this.lastEntries = lastEntries;
    }

    public BlogEntry getLatestEntry() {
        return latestEntry;
    }

    public void setLatestEntry(BlogEntry latestEntry) {
        this.latestEntry = latestEntry;
    }
}
