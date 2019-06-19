package com.company;

import java.io.*;

public class Line_Numbers {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/resources/inputLineNumbers.txt";
        String outputPath = userDir + "/output.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            Integer count = 1;
            String line;
            while ((line = bfr.readLine()) != null) {
                bfw.write(String.format("%d. %s", count++, line));
                bfw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
