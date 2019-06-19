package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Diagonal_Difference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        int[][] matrix = new int[n][];

        for (int row = 0; row < n; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int primarySumDiagonal = 0;
        int secondarySumDiagonal = 0;

        for (int row = 0; row < n; row++) {
            primarySumDiagonal += matrix[row][row];
            secondarySumDiagonal += matrix[row][matrix.length - row - 1];
        }

        System.out.println(Math.abs(primarySumDiagonal - secondarySumDiagonal));
    }
}
