package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Browser_History_Upgrade {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> forwardUrls = new ArrayDeque<>();
        String line = "";

        while(!"Home".equals(line = sc.nextLine())) {
            if (line.equals("back")) {
                if (stack.size() > 1) {
                    forwardUrls.push(stack.pop());
                    System.out.println(stack.peek());
                } else {
                    System.out.println("no previous URLs");
                }
            } else if (line.equals("forward")) {
                if (forwardUrls.isEmpty()) {
                    System.out.println("no next URLs");
                } else {
                    String el = forwardUrls.pop();
                    stack.push(el);
                    System.out.println(el);
                }
            } else {
                stack.push(line);
                forwardUrls.clear();
                System.out.println(line);
            }
        }
    }
}
