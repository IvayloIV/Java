package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Math_Potato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] people = sc.nextLine().split("\\s+");
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (String person : people) {
            queue.offer(person);
        }
        Integer n = Integer.parseInt(sc.nextLine());

        Integer count = 1;
        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                String firstPerson = queue.poll();
                queue.offer(firstPerson);
            }

            if (isPrime(count)) {
                System.out.println("Prime " + queue.peek());
            } else {
                System.out.println("Removed " + queue.poll());
            }

            count++;
        }

        System.out.println("Last is " + queue.poll());
    }

    private static boolean isPrime(Integer num) {
        if (num == 0 || num == 1) {
            return  false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return  true;
    }
}
