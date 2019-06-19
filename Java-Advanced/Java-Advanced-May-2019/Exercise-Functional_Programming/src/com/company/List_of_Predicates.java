package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class List_of_Predicates {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<List<Integer>, Integer> isDivided = (dividedNums, num) -> {
            for (Integer d : dividedNums) {
                if (num % d != 0) {
                    return  false;
                }
            }

            return  true;
        };
        Consumer<Integer> printData = a -> System.out.print(a + " ");

        Integer upperBound = Integer.parseInt(bufferedReader.readLine());
        List<Integer> dividedNums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        IntStream.rangeClosed(1, upperBound)
                .boxed()
                .filter(a -> isDivided.test(dividedNums, a))
                .forEach(printData);
    }
}
