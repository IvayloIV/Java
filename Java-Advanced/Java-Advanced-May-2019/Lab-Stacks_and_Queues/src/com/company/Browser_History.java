package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Browser_History {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        String line = "";

        while(!"Home".equals(line = sc.nextLine())) {
            if (!line.equals("back")) {
                stack.push(line);
                System.out.println(line);
            } else {
                if (stack.size() > 1) {
                    stack.pop();
                    System.out.println(stack.peek());
                } else {
                    System.out.println("no previous URLs");
                }
            }
        }
    }
}
