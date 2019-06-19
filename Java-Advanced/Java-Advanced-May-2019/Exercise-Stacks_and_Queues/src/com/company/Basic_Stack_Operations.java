package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Basic_Stack_Operations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Integer numToPush = tokens[0];
        Integer numsToPop = tokens[1];
        Integer neededNums = tokens[2];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < numToPush; i++) {
            stack.push(nums[i]);
        }

        for (int i = 0; i < numsToPop; i++) {
            if (stack.isEmpty()) {
                break;
            }
            stack.pop();
        }

        if (stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        Integer minNum = stack.pop();
        if (neededNums.equals(minNum)) {
            System.out.println("true");
            return;
        }

        while (!stack.isEmpty()) {
            Integer num = stack.pop();
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
