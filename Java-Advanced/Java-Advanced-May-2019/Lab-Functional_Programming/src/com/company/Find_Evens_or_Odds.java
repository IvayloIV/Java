package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Find_Evens_or_Odds {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = bufferedReader.readLine().split("\\s+");

        int lowerBound = Integer.parseInt(tokens[0]);
        int upperBound = Integer.parseInt(tokens[1]);
        String type = bufferedReader.readLine();

        Predicate<Integer> filterType = GetFilterType(type);
        Consumer<Integer> printType = a -> System.out.print(a + " ");

        IntStream.rangeClosed(lowerBound, upperBound)
                .boxed()
                .filter(filterType)
                .forEach(printType);
    }

    private static Predicate<Integer> GetFilterType(String type) {
        Predicate<Integer> even = a -> Math.abs(a) % 2 == 0;
        Predicate<Integer> odd = a -> Math.abs(a) % 2 == 1;

        if (type.equals("even")) {
            return even;
        }
        return odd;
    }
}
