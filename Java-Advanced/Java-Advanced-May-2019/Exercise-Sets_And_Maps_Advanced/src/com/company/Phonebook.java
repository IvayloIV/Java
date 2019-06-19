package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> phonebook = new HashMap<>();
        String input;

        while (!"search".equals(input = sc.nextLine())) {
            String[] tokens = input.split("-");
            String name = tokens[0];
            String phone = tokens[1];

            phonebook.put(name, phone);
        }

        while (!"stop".equals(input = sc.nextLine())) {
            if (!phonebook.containsKey(input)) {
                System.out.println(String.format("Contact %s does not exist.", input));
            } else {
                System.out.println(String.format("%s -> %s", input, phonebook.get(input)));
            }
        }
    }
}
