package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Fix_Emails {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> people = new LinkedHashMap<>();
        String name;
        while (!"stop".equals(name = sc.nextLine())) {
            String email = sc.nextLine();

            String emailToLower = email.toLowerCase();
            if (emailToLower.endsWith("us") ||
                    emailToLower.endsWith("uk") ||
                    emailToLower.endsWith("com")) {
                continue;
            }

            people.put(name, email);
        }

        people.forEach((n, e) -> System.out.println(String.format("%s -> %s", n, e)));
    }
}
