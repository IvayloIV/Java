package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;

public class Predicate_For_Names {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<String, Integer> isNameLessThanN = (name, n) -> name.length() <= n;

        Integer n = Integer.parseInt(bufferedReader.readLine());
        String[] names = bufferedReader.readLine().split("\\s+");
        Arrays.stream(names)
                .filter(a -> isNameLessThanN.test(a, n))
                .forEach(System.out::println);
    }
}
