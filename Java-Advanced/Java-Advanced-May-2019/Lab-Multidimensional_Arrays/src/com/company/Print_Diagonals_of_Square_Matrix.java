package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Print_Diagonals_of_Square_Matrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        int[][] matrix = new int[n][];

        for (int row = 0; row < n; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int row = 0; row < n; row++) {
            System.out.print(matrix[row][row] + " ");
        }
        System.out.println();

        for (int row = 0; row < n; row++) {
            System.out.print(matrix[n - row - 1][row] + " ");
        }
    }
}
