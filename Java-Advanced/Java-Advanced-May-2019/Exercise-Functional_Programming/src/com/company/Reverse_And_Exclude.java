package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Reverse_And_Exclude {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<Integer, Integer> divideByNum = (num, divideNum) -> num % divideNum != 0;
        Consumer<Integer> printData = n -> System.out.print(n + " ");

        List<Integer> nums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Integer dividedNum = Integer.parseInt(bufferedReader.readLine());

        nums = nums.stream()
                .filter(a -> divideByNum.test(a, dividedNum))
                .collect(Collectors.toList());

        Collections.reverse(nums);
        nums.forEach(printData);
    }
}
