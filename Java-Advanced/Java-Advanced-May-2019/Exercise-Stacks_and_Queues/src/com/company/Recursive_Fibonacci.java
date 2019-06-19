package com.company;

import java.util.Scanner;

public class Recursive_Fibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        long[] arr = new long[n + 1];
        System.out.println(getFibonacci(n, arr));
    }

    private static Long getFibonacci(Integer n, long[] arr) {
        if (arr[n] != 0) {
            return arr[n];
        }

        if (n == 0 || n == 1) {
            return 1L;
        }

        arr[n] = getFibonacci(n - 1, arr) + getFibonacci(n - 2, arr);
        return arr[n];
    }
}
