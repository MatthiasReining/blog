/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import java.io.*;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mre
 */
public class CopyingMachine {

    public void copyDirectory(String sourceDirectoryPath, String targetDirectoryPath) {
        copyDirectory(new File(sourceDirectoryPath), new File(targetDirectoryPath));
    }

    public void copyDirectory(File sourceDir, File targetDir) {

        for (File sourceFile : sourceDir.listFiles()) {
            File targetFile = new File(targetDir.getAbsolutePath() + File.separator + sourceFile.getName());

            if (sourceFile.isDirectory()) {
                if (!targetFile.exists())
                    targetFile.mkdir();
                copyDirectory(sourceFile, targetFile);
            } else
                copyFile(sourceFile, targetFile);
        }
    }

    public void copyFile(File sourceFile, File targetFile) {
        //thx to Fabi (http://disq.us/8cga2n)
        Path sourcePath = FileSystems.getDefault().getPath(sourceFile.getAbsolutePath());
        Path destPath = FileSystems.getDefault().getPath(targetFile.getAbsolutePath());
        try {
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException ex) {
            Logger.getLogger(CopyingMachine.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Deprecated
    public void copyFileJDK6(File inputFile, File outputFile) {
        System.out.println("copy " + inputFile.getName() + " to " + 
                outputFile.getAbsolutePath());
        try (FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] b = new byte[1024];
            int noOfBytes;
            while ((noOfBytes = fis.read(b)) != -1) {
                fos.write(b, 0, noOfBytes);
            }
        } catch (IOException ex) {
             Logger.getLogger(CopyingMachine.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
