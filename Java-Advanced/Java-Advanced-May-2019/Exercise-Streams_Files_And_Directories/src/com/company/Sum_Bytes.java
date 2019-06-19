package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sum_Bytes {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/resources/input.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath))) {
            Long totalSum = 0L;
            String line;
            while ((line = bfr.readLine()) != null) {
                char[] chars = line.toCharArray();

                for (char currentChar : chars) {
                    totalSum += currentChar;
                }
            }

            System.out.println(totalSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
