package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Balanced_Parentheses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Character> brackets = new ArrayDeque<>();
        String line = sc.nextLine();
        for (int i = 0; i < line.length(); i++) {
            Character c = line.charAt(i);

            if (c.equals('{') || c.equals('(') || c.equals('[')) {
                brackets.push(c);
            } else {
                if (brackets.isEmpty()) {
                    System.out.println("NO");
                    return;
                }
                Character topBracket = brackets.pop();

                if ((topBracket.equals('(') && !c.equals(')')) ||
                    (topBracket.equals('{') && !c.equals('}')) ||
                    (topBracket.equals('[') && !c.equals(']'))) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }
}
