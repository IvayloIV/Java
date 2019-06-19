package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sum_Lines {

    public static void main(String[] args) {
	    String userDir = System.getProperty("user.dir");

	    String inputPath = userDir + "/resources/input.txt";

	    try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                char[] chars = line.toCharArray();
                Integer sum = 0;

                for (int i = 0; i < chars.length; i++) {
                    char currentChar = chars[i];
                    sum += currentChar;
                }

                System.out.println(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
