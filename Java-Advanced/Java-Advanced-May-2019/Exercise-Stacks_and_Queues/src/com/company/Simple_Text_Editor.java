package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Simple_Text_Editor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        ArrayDeque<String> stack = new ArrayDeque<>();
        String text = "";
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            if (line.equals("4")) {
                text = stack.pop();
            } else {
                text = ProsesCommand(line, text, stack);
            }
        }
    }

    private static String ProsesCommand(String line, String text, ArrayDeque<String> stack) {
        String[] tokens = line.split("\\s+");
        String command = tokens[0];

        if (command.equals("1")) {
            stack.push(text);
            text += tokens[1];
        } else if (command.equals("2")) {
            stack.push(text);
            Integer count = Integer.parseInt(tokens[1]);
            text = text.substring(0, text.length() - count);
        } else if (command.equals("3")){
            Integer index = Integer.parseInt(tokens[1]);
            System.out.println(text.charAt(index - 1));
        }

        return text;
    }
}
