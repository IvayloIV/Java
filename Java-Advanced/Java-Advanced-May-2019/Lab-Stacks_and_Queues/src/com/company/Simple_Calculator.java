package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Simple_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        String[] line = sc.nextLine().split("\\s+");
        for (int i = line.length - 1; i >= 0; i--) {
            stack.push(line[i]);
        }

        while(stack.size() > 1) {
            Integer firstNum = Integer.parseInt(stack.pop());
            String operator = stack.pop();
            Integer secondNum = Integer.parseInt(stack.pop());

            if (operator.equals("+")) {
                stack.push(firstNum + secondNum + "");
            } else {
                stack.push(firstNum - secondNum + "");
            }
        }

        System.out.println(stack.pop());
    }
}
