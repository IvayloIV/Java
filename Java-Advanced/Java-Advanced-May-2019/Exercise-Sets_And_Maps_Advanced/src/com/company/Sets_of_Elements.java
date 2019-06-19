package com.company;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Sets_of_Elements {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int firstCollectionCount = tokens[0];
        int secondCollectionCount = tokens[1];

        for (int i = 0; i < firstCollectionCount; i++) {
            firstSet.add(Integer.parseInt(sc.nextLine()));
        }

        for (int i = 0; i < secondCollectionCount; i++) {
            secondSet.add(Integer.parseInt(sc.nextLine()));
        }

        for (Integer num : firstSet) {
            if (secondSet.contains(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
