package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Matching_Brackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        String line = sc.nextLine();

        for (int i = 0; i < line.length(); i++) {
            Character el = line.charAt(i);

            if (el.equals('(')) {
                stack.push(i);
            } else if (el.equals(')')) {
                Integer startIndex = stack.pop();
                System.out.println(line.substring(startIndex, i + 1));
            }
        }
    }
}
