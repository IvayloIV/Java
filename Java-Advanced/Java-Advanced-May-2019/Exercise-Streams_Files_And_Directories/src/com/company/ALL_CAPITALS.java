package com.company;

import java.io.*;

public class ALL_CAPITALS {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/resources/input.txt";
        String outputPath = userDir + "/output.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                bfw.write(line.toUpperCase());
                bfw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
