package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Word_Count {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String wordsPath = userDir + "/resources/words.txt";
        String textPath = userDir + "/resources/text.txt";
        String outputPath = userDir + "/result.txt";

        Map<String, Integer> words = new HashMap<>();

        try (BufferedReader bfrWords = new BufferedReader(new FileReader(wordsPath));
             BufferedReader bfrText = new BufferedReader(new FileReader(textPath));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath))) {
            String[] allWords = bfrWords.readLine().split("\\s+");

            for (String word : allWords) {
                words.putIfAbsent(word, 0);
            }

            String[] textWords = bfrText.readLine().split("\\s+");
            for (String word : textWords) {
                if (words.containsKey(word)) {
                    words.put(word, words.get(word) + 1);
                }
            }

            words.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).forEach(kvp -> {
                String format = String.format("%s - %d", kvp.getKey(), kvp.getValue());
                try {
                    bfw.write(format + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
