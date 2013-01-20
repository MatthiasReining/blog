/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

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
    private void create() {
        latestEntry = ep.getEntries().get(0);
        lastEntries = ep.getEntries().subList(1, ep.getEntries().size());        
    }
    
    private Entry latestEntry;
    
    private List<Entry> lastEntries;

    public List<Entry> getLastEntries() {
        return lastEntries;
    }

    public void setLastEntries(List<Entry> lastEntries) {
        this.lastEntries = lastEntries;
    }

    public Entry getLatestEntry() {
        return latestEntry;
    }

    public void setLatestEntry(Entry latestEntry) {
        this.latestEntry = latestEntry;
    }
}
