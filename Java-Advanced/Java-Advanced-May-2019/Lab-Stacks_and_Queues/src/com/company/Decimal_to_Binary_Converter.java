package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Decimal_to_Binary_Converter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer num = Integer.parseInt(sc.nextLine());
        ArrayDeque<Number> stack = new ArrayDeque<>();

        if (num.equals(0)) {
            System.out.println(0);
        }

        while (num > 0) {
            stack.push(num % 2);
            num /= 2;
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
