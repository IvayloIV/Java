package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Maximum_Element {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> nums = new ArrayDeque<>();
        Integer n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String command = sc.nextLine();

            if (command.equals("2")) {
                if (!nums.isEmpty()) {
                    nums.pop();
                }
            } else if (command.equals("3")) {
                printMaxElement(nums);
            } else {
                String[] tokens = command.split("\\s+");
                Integer num = Integer.parseInt(tokens[1]);
                nums.push(num);
            }
        }
    }

    private static void printMaxElement(ArrayDeque<Integer> nums) {
        Integer maxNum = Integer.MIN_VALUE;

        ArrayDeque<Integer> stack = new ArrayDeque<>(nums);
        while (!stack.isEmpty()) {
            Integer currentNum = stack.pop();

            if (maxNum < currentNum) {
                maxNum = currentNum;
            }
        }

        System.out.println(maxNum);
    }
}
