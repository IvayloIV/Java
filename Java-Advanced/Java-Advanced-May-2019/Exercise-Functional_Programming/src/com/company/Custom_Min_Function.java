package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Custom_Min_Function {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Function<int[], Integer> getTheSmallestNum = nums -> {
            Integer min = Integer.MAX_VALUE;

            for (Integer num : nums) {
                if (num < min) {
                    min = num;
                }
            }

            return min;
        };

        int[] nums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(getTheSmallestNum.apply(nums));
    }
}
