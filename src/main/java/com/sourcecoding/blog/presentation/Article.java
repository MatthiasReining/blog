/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.presentation;

import com.sourcecoding.blog.EntryProcessor;
import com.sourcecoding.blog.model.BlogEntry;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mre
 */
@Named
@RequestScoped
public class Article {
    @Inject
    EntryProcessor ep;
    
    private BlogEntry entry;

    
    @PostConstruct
    void init() {
        String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
        
        entry = ep.getEntryByKey(key);
                
    }
    
    
    public BlogEntry getEntry() {
        return entry;
    }
}
