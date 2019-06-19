package com.company;

import java.io.*;

public class Copy_a_Picture {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/resources/cat.jpg";
        String outputPath = userDir + "/output.jpg";

        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            Integer currentByte;

            while((currentByte = fis.read()) >= 0) {
                fos.write(currentByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
