package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Reverse_Matrix_Diagonals {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rowCount = tokens[0];
        int colCount = tokens[1];

        int[][] matrix = new int[rowCount][];
        for (int row = 0; row < rowCount; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int col = colCount - 1; col >= 0; col--) {
            PrintDiagonal(matrix, rowCount - 1, col);            
        }

        for (int row = rowCount - 2; row >= 0; row--) {
            PrintDiagonal(matrix, row, 0);
        }
    }

    private static void PrintDiagonal(int[][] matrix, int row, int col) {
        System.out.print(matrix[row][col] + " ");

        while (isInside(matrix, row - 1, col + 1)) {
            row -= 1;
            col += 1;
            System.out.print(matrix[row][col] + " ");
        }
        System.out.println();
    }

    private static boolean isInside(int[][] matrix, int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
    }
}
