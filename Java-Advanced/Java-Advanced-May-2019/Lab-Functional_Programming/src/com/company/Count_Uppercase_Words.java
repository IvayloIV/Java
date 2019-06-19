package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Count_Uppercase_Words {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Predicate<String> isUpperStart = el -> Character.isUpperCase(el.charAt(0));

        List<String> words = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .filter(isUpperStart)
                .collect(Collectors.toList());

        System.out.println(words.size());
        System.out.println(String.join(System.lineSeparator(), words));
    }
}
