package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Custom_Comparator {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        Comparator<Integer> sortNums = (a, b) -> {
            Boolean aEvent = isEven.test(a);
            Boolean bEvent = isEven.test(b);
            if (aEvent && !bEvent) {
                return -1;
            } else if (!aEvent && bEvent) {
                return 1;
            } else {
                return a - b;
            }
        };
        Consumer<Integer> printData = n -> System.out.print(n + " ");

        List<Integer> nums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.sort(nums, sortNums);
        nums.forEach(printData);
    }
}
