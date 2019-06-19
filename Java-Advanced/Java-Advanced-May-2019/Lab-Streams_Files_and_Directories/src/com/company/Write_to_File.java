package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Write_to_File {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/input.txt";
        String outputPath = userDir + "/output.txt";

        Set<Character> forbiddenSymbols = new HashSet<>(
                Arrays.asList('.', ',', '!', '?')
        );

        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            int textByte;

            while ((textByte = fis.read()) >= 0) {
                if (!forbiddenSymbols.contains((char)textByte)) {
                    fos.write(textByte);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
