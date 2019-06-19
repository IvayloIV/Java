package com.company;

import java.util.*;

public class Count_Real_Numbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Double, Integer> numbersCount = new LinkedHashMap<>();
        double[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        for (double num : nums) {
            if (!numbersCount.containsKey(num)) {
                numbersCount.put(num, 0);
            }

            numbersCount.put(num, numbersCount.get(num) + 1);
        }

        numbersCount.forEach((num, count) -> {
            System.out.println(String.format("%.1f -> %d", num, count));
        });
    }
}
