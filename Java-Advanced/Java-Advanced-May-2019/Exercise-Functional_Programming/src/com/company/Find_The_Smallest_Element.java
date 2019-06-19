package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Find_The_Smallest_Element {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Function<List<Integer>, Integer> findTheSmallestIndexEl = nums -> {
            Integer minNum = Integer.MAX_VALUE;
            Integer maxIndex = 0;

            for (int i = 0; i < nums.size(); i++) {
                Integer num = nums.get(i);

                if (num <= minNum) {
                    minNum = num;
                    maxIndex = i;
                }
            }

            return maxIndex;
        };

        List<Integer> nums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Integer theSmallestIndex = findTheSmallestIndexEl.apply(nums);
        System.out.println(theSmallestIndex);
    }
}
