package com.softuni.bookshop.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtil {
    private static final String BASE_PATH = "src/main/java/com/softuni/bookshop/files/";

    public static List<String> readFile(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        String line;
        BufferedReader booksReader = new BufferedReader(new FileReader(BASE_PATH + fileName));
        while ((line = booksReader.readLine()) != null) {
            result.add(line);
        }

        return result;
    }
}
