package com.company;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUni_Party {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> treeSet = new TreeSet<>();
        String input;
        while (!"PARTY".equals(input = sc.nextLine())) {
            treeSet.add(input);
        }

        while (!"END".equals(input = sc.nextLine())) {
            treeSet.remove(input);
        }

        System.out.println(treeSet.size());
        System.out.println(String.join(System.lineSeparator(), treeSet));
    }
}
