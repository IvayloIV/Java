package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sort_Even_Numbers {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> eventNums = Arrays.stream(bufferedReader.readLine().split(", "))
                .map(Integer::parseInt)
                .filter(a -> a % 2 == 0)
                .collect(Collectors.toList());

        List<String> eventNumsStr = ConvertToStrings(eventNums);
        System.out.println(String.join(", ", eventNumsStr));

        eventNums.sort(Comparator.comparingInt(a -> a));

        List<String> sortedNumsStr = ConvertToStrings(eventNums);
        System.out.println(String.join(", ", sortedNumsStr));
    }

    private static List<String> ConvertToStrings(List<Integer> nums) {
        return nums.stream()
                .map(a -> a.toString())
                .collect(Collectors.toList());
    }
}
