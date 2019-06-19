package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Hot_Potato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] people = sc.nextLine().split("\\s+");
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (String person : people) {
            queue.offer(person);
        }
        Integer n = Integer.parseInt(sc.nextLine());

        while(queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                String firstPerson = queue.poll();
                queue.offer(firstPerson);
            }

            System.out.println("Removed " + queue.poll());
        }

        System.out.println("Last is " + queue.poll());
    }
}
