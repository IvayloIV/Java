package com.company;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Unique_Usernames {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> usernames = new LinkedHashSet<>();
        Integer n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String username = sc.nextLine();
            usernames.add(username);
        }

        System.out.println(String.join(System.lineSeparator(), usernames));
    }
}
