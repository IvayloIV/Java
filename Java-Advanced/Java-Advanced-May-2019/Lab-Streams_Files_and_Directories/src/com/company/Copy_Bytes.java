package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_Bytes {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/input.txt";
        String outputPath = userDir + "/output.txt";

        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            int textByte;

            while ((textByte = fis.read()) >= 0) {
                if (textByte == 10 || textByte == 32) {
                    fos.write(textByte);
                } else {
                    String textByteStr = String.valueOf(textByte);

                    for (int i = 0; i < textByteStr.length(); i++) {
                        char c = textByteStr.charAt(i);
                        fos.write(c);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
