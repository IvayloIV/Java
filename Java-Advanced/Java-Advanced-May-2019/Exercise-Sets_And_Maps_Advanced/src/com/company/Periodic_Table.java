package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Periodic_Table {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> elements = new TreeSet<>();
        Integer n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            List<String> tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                    .collect(Collectors.toList());
            elements.addAll(tokens);
        }

        System.out.println(String.join(" ", elements));
    }
}
