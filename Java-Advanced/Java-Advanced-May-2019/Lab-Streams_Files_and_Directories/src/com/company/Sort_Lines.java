package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Sort_Lines {

    public static void main(String[] args) throws IOException {
        String userDir = System.getProperty("user.dir");

        Path inputPath = Paths.get(userDir + "/input.txt");
        Path outputPath = Paths.get(userDir + "/output.txt");

        List<String> lines = Files.readAllLines(inputPath).stream()
                .filter(a -> !a.isEmpty())
                .sorted()
                .collect(Collectors.toList());

        Files.write(outputPath, lines);
    }
}
