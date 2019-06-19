package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sum_Numbers {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Function<String, Integer> parser = a -> Integer.parseInt(a);
        BinaryOperator<Integer> sumFunc = (curr, acc) -> curr + acc;

        List<Integer> nums = Arrays.stream(bufferedReader.readLine().split(", "))
                    .map(parser)
                    .collect(Collectors.toList());

        Integer sum = nums.stream().reduce(0, sumFunc);

        System.out.println("Count = " + nums.size());
        System.out.println("Sum = " + sum);
    }
}
