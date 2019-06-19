package com.company;

import java.io.*;
import java.util.*;

public class Count_Character_Types {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/resources/input.txt";
        String outputPath = userDir + "/output.txt";

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        Set<Character> punctuation = new HashSet<>(Arrays.asList('!', ',', '.', '?'));

        try (BufferedReader bfr = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            Integer vowelsCount = 0;
            Integer consonantsCount = 0;
            Integer punctuationCount = 0;

            String line;
            while ((line = bfr.readLine()) != null) {
                char[] charLine = line.toCharArray();

                for (char charElement : charLine) {
                    if (charElement == ' ') {
                        continue;
                    }

                    if (vowels.contains(charElement)) {
                        vowelsCount++;
                    } else if (punctuation.contains(charElement)) {
                        punctuationCount++;
                    } else {
                        consonantsCount++;
                    }
                }
            }

            bfw.write("Vowels: " + vowelsCount + System.lineSeparator());
            bfw.write("Consonants: " + consonantsCount + System.lineSeparator());
            bfw.write("Punctuation: " + punctuationCount + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
