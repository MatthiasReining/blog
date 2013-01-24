/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog.business.build.control;

import java.io.File;
import org.junit.Test;

/**
 *
 * @author mre
 */
public class CopyingMachineTest {

    @Test
    public void copyOneFile() {
        CopyingMachine cp = new CopyingMachine();

        String baseDir = new File("").getAbsolutePath() + File.separator;
        File targetDir = new File(baseDir + "target/copyTest");
        targetDir.mkdir();

        File sourceDir = new File(baseDir + "src/main/resources/web-resources");
        System.out.println(sourceDir.getAbsolutePath());
        cp.copy(sourceDir, targetDir);

    }
}
