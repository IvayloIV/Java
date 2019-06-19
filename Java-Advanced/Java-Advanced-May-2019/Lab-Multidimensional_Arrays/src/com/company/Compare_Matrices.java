package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Compare_Matrices {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] matrix1 = CreateMatrix(sc);
        int[][] matrix2 = CreateMatrix(sc);
        boolean isEqual = CompareMatrix(matrix1, matrix2);
        System.out.println(isEqual ? "equal" : "not equal");
    }

    private static boolean CompareMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length) {
            return false;
        }

        for (int row = 0; row < matrix1.length; row++) {
            if (matrix1[row].length != matrix2[row].length) {
                return false;
            }

            for (int col = 0; col < matrix1[row].length; col++) {
                if (matrix1[row][col] != matrix2[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] CreateMatrix(Scanner sc) {
        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowCount = tokens[0];
        int colCount = tokens[1];
        int[][] matrix = new int[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            int[] nums =  Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < colCount; col++) {
                matrix[row][col] = nums[col];
            }
        }

        return  matrix;
    }
}
