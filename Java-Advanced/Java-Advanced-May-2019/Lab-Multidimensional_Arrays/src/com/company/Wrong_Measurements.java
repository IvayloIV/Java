package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Wrong_Measurements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[n][];
        int[][] originalMatrix = new int[n][];
        for (int row = 0; row < matrix.length; row++) {
            int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            matrix[row] = new int[nums.length];
            originalMatrix[row] = new int[nums.length];
            for (int col = 0; col < nums.length; col++) {
                matrix[row][col] = nums[col];
                originalMatrix[row][col] = nums[col];
            }
        }

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowN = tokens[0];
        int colN = tokens[1];
        int num = matrix[rowN][colN];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (num == originalMatrix[row][col]) {
                    matrix[row][col] = SumNeighbours(row, col, originalMatrix, num);
                }
            }
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row).replaceAll("[\\[\\],]", ""));
        }
    }

    private static int SumNeighbours(int row, int col, int[][] matrix, int n) {
        int sum = 0;
        sum += AddCell(row - 1, col, matrix, n);
        sum += AddCell(row + 1, col, matrix, n);
        sum += AddCell(row, col - 1, matrix, n);
        sum += AddCell(row, col + 1, matrix, n);
        return  sum;
    }

    private static int AddCell(int row, int col, int[][] matrix, int n) {
        if (isInside(row, col, matrix) && matrix[row][col] != n) {
            return matrix[row][col];
        }

        return 0;
    }

    private static boolean isInside(int row, int col, int[][] matrix) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
    }
}
