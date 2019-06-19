package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Merge_Two_Files {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputOnePath = userDir + "/resources/inputOne.txt";
        String inputTwoPath = userDir + "/resources/inputTwo.txt";
        String outputPath = userDir + "/result.txt";

        List<String> lines = new ArrayList<>();

        try (BufferedReader bfrOne = new BufferedReader(new FileReader(inputOnePath));
             BufferedReader bfrTwo = new BufferedReader(new FileReader(inputTwoPath));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            AddElements(lines, bfrOne);
            AddElements(lines, bfrTwo);

            for (String line : lines) {
                bfw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void AddElements(List<String> lines, BufferedReader bfr) throws IOException {
        String line;

        while((line = bfr.readLine()) != null) {
            lines.add(line);
        }
    }
}
