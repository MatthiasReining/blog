package com.sourcecoding.blog.app;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sourcecoding.blog.business.build.boundary.BlogBuilder;
import com.sourcecoding.blog.business.configuration.entity.Configuration;
import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import winstone.Launcher;

/**
 *
 * @author mre
 */
public class Main {

    public static void main(String... args) throws Exception {
        String exportPath;

        if (args.length > 0)
            exportPath = args[0];
        else
            exportPath = "D:\\java-server\\tomcat\\apache-tomcat-7.0.34\\webapps\\blog";

        String myBlogRootPath = "D:\\labs\\my-blog\\";

        Configuration config = new Configuration();
        config.setBlogPath("");
        config.setFreemarkerTemplateDirectoryPath(myBlogRootPath + "resources\\templates");
        config.setHtmlExportRootDirectoryPath(exportPath);
        config.setMarkdownContentDirectoryPath(myBlogRootPath + "resources\\content");
        config.setWebResourcesDirctoryPath(myBlogRootPath + "resources\\web-resources");

        BlogBuilder bb = new BlogBuilder();
        
        bb.generateContent(config);
        
        //Start winston http server
        Map<String, String> param = new HashMap<>();
        param.put("webroot", exportPath); // or any other command line args, eg port
        Launcher.initLogger(param);
        new Launcher(param); // spawns threads, so your application doesn't block

        
        Thread.sleep(1000); //wait for server start
        
        System.out.println("Shutdown Webserver by Ctrl+C");
        java.awt.Desktop.getDesktop().browse(new URI("http://localhost:8080"));

        //check for modificaitons
        long modified = createFileModificationIndicator(config);
        while (true) {
            Thread.sleep(10000);
            long newModified = createFileModificationIndicator(config);
            if (modified != newModified) {
                modified = newModified;
                bb.generateContent(config);
            }
        }
        // before shutdown
        //winstone.shutdown();
    }

    private static long createFileModificationIndicator(Configuration config) {
        File mdDirectory = new File(config.getMarkdownContentDirectoryPath());
        File[] mdFiles = mdDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".md"))
                    return true;
                return false;
            }
        });
        long result = 0l;
        for (File f : mdFiles) {
            result += f.lastModified();
        }
        return result;
    }

   
}
