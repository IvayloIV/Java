package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Count_Symbols {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Character, Integer> elementsCount = new TreeMap<>();
        char[] input = sc.nextLine().toCharArray();
        for (char c : input) {
            elementsCount.putIfAbsent(c, 0);
            elementsCount.put(c, elementsCount.get(c) + 1);
        }

        elementsCount.forEach((element, count) ->
                System.out.println(String.format("%s: %d time/s", element, count)));
    }
}
