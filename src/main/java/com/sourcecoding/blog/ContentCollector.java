/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.model.BlogEntry;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author mre
 */
class ContentCollector {

    public static List<BlogEntry> collect() {
        try {
            EntryProcessor ep = new EntryProcessor();
            ep.init();
            return ep.getEntries();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }



}
