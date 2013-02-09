/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author mre
 */
public class SitemapPinger {

    private String[] searchEngines = new String[]{
        "http://www.google.com/webmasters/sitemaps/ping?sitemap=",
        "http://www.bing.com/webmaster/ping.aspx?siteMap=",
        "http://submissions.ask.com/ping?sitemap="
    };

    public void ping(String url2Sitemap) {

        for (String searchEngine : searchEngines) {
            searchEngine += url2Sitemap;
            pingURL(searchEngine);
        }
    }

    private void pingURL(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            int statusCode = connection.getResponseCode();
            System.out.println("Call " + url + " / statusCode: " + statusCode);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Bad URL: " + url, e);
        } catch (IOException e) {
            System.out.println("Service " + url + " unavailable, oh no!");
        }
    }
}
