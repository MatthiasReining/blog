/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.model.BlogEntry;
import java.text.ParseException;
import org.junit.Test;

/**
 *
 * @author mre
 */
public class EntryProcessorTest {

    @Test
    public void shouldReadMDFile() throws ParseException {

        EntryProcessor ep = new EntryProcessor();
        ep.init();


        for (BlogEntry be : ep.getEntries()) {
            System.out.println(be.getTitle());
            System.out.println(be.getHtmlContent());
        }

    }
}