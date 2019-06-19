package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Basic_Queue_Operations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Integer numToOffer = tokens[0];
        Integer numsToPoll = tokens[1];
        Integer neededNums = tokens[2];

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < numToOffer; i++) {
            queue.offer(nums[i]);
        }

        for (int i = 0; i < numsToPoll; i++) {
            if (queue.isEmpty()) {
                break;
            }
            queue.poll();
        }

        if (queue.isEmpty()) {
            System.out.println(0);
            return;
        }

        Integer minNum = queue.poll();
        if (neededNums.equals(minNum)) {
            System.out.println("true");
            return;
        }

        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            if (neededNums.equals(num)) {
                System.out.println("true");
                return;
            }

            if (num < minNum) {
                minNum = num;
            }
        }

        System.out.println(minNum);
    }
}
