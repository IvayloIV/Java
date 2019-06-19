package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Maximal_Sum {
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

        Integer maxSum = Integer.MIN_VALUE;
        Integer startRow = 0;
        Integer startCol = 0;

        for (int row = 0; row < rowCount - 2; row++) {
            for (int col = 0; col < colCount - 2; col++) {
                int sum = 0;

                for (int innerRow = row; innerRow <= row + 2; innerRow++) {
                    for (int innerCol = col; innerCol <= col + 2; innerCol++) {
                        sum += matrix[innerRow][innerCol];
                    }
                }

                if (sum > maxSum) {
                    maxSum = sum;
                    startRow = row;
                    startCol = col;
                }
            }
        }

        System.out.println("Sum = " + maxSum);
        for (int row = startRow; row <= startRow + 2; row++) {
            for (int col = startCol; col <= startCol + 2; col++) {
                System.out.print(matrix[row][col] + " ");
            }

            System.out.println();
        }
    }
}
