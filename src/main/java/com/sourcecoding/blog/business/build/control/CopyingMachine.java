/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import java.io.*;

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
                    targetFile.mkdir();;
                copyDirectory(sourceFile, targetFile);
            } else
                copyFile(sourceFile, targetFile);
        }
    }

    public void copyFile(File inputFile, File outputFile) {
        try {
            System.out.println("copy " + inputFile.getName() + " to " + outputFile.getAbsolutePath());
            FileReader in = new FileReader(inputFile);
            try (FileWriter out = new FileWriter(outputFile)) {
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
