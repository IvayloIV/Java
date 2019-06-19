package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Fill_the_Matrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split(", ");
        Integer n = Integer.parseInt(tokens[0]);
        String pattern = tokens[1];

        int[][] matrix = new int[n][n];

        int count = 1;
        for (int row = 0; row < matrix.length; row++) {
            if (pattern.equals("A") || row % 2 == 0) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[col][row] = count++;
                }
            } else if (pattern.equals("B")) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[matrix[row].length - 1 - col][row] = count++;
                }
            }
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row).replaceAll("[]\\[,]", ""));
        }
    }
}
