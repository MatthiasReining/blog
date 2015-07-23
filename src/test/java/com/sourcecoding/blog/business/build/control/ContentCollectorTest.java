/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import java.io.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author mre
 */
@Ignore
public class ContentCollectorTest {

    public ContentCollectorTest() {
    }

    @Test
    public void testCheckout() throws IOException {

        Process p = Runtime.getRuntime().exec("git pull", null, new File("D:\\labs\\blog-export\\gitblog\\blog"));
        Reader r = new InputStreamReader(p.getInputStream());
        try (BufferedReader in = new BufferedReader(r)) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("mit git fertig...");

    }
}
