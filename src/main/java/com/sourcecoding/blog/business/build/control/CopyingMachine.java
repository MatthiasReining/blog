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

    public void copy(String sourceDirectoryPath, String targetDirectoryPath) {
        copy(new File(sourceDirectoryPath), new File(targetDirectoryPath));
    }

    public void copy(File sourceDir, File targetDir) {

        for (File sourceFile : sourceDir.listFiles()) {
            File targetFile = new File(targetDir.getAbsolutePath() + File.separator + sourceFile.getName());

            if (sourceFile.isDirectory()) {
                if (!targetFile.exists())
                    targetFile.mkdir();;
                copy(sourceFile, targetFile);
            } else
                copyFile(sourceFile, targetFile);
        }
    }

    private void copyFile(File inputFile, File outputFile) {
        try {
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
