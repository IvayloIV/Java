package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Maximum_Sum_of_2x2_Submatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowCount = tokens[0];
        int colCount = tokens[1];

        int[][] matrix = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Integer maxSum = Integer.MIN_VALUE;
        Integer startRow = 0;
        Integer startCol = 0;

        for (int row = 0; row < rowCount - 1; row++) {
            for (int col = 0; col < colCount - 1; col++) {
                int sum = matrix[row][col] + matrix[row][col + 1]
                        + matrix[row + 1][col] + matrix[row + 1][col + 1];

                if (sum > maxSum) {
                    maxSum = sum;
                    startRow = row;
                    startCol = col;
                }
            }
        }

        for (int row = startRow; row <= startRow + 1; row++) {
            for (int col = startCol; col <= startCol + 1; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println(maxSum);
    }
}
