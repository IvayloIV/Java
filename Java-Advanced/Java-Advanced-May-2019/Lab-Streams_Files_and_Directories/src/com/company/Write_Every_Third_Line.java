package com.company;

import java.io.*;

public class Write_Every_Third_Line {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/input.txt";
        String outputPath = userDir + "/output.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            int count = 1;
            String line;

            while ((line = bfr.readLine()) != null) {
                if (count++ % 3 == 0) {
                    bfw.write(line);
                    bfw.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
