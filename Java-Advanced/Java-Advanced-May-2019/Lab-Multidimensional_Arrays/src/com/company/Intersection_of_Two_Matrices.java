package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Intersection_of_Two_Matrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rowCount = Integer.parseInt(sc.nextLine());
        int colCount = Integer.parseInt(sc.nextLine());

        char[][] matrix1 = CreateMatrix(rowCount, colCount, sc);
        char[][] matrix2 = CreateMatrix(rowCount, colCount, sc);

        char[][] intersectMatrix = CreateIntersectMatrix(matrix1, matrix2);
        PrintMatrix(intersectMatrix);
    }

    private static void PrintMatrix(char[][] intersectMatrix) {
        for (char[] row : intersectMatrix) {
            System.out.println(Arrays.toString(row).replaceAll("[]\\[,]", ""));
        }
    }

    private static char[][] CreateIntersectMatrix(char[][] matrix1, char[][] matrix2) {
        char[][] intersectMatrix = new char[matrix1.length][matrix1[0].length];

        for (int row = 0; row < matrix1.length; row++) {
            for (int col = 0; col < matrix1[row].length; col++) {
                if (matrix1[row][col] != matrix2[row][col]) {
                    intersectMatrix[row][col] = '*';
                } else {
                    intersectMatrix[row][col] = matrix1[row][col];
                }
            }
        }

        return intersectMatrix;
    }

    private static char[][] CreateMatrix(int rowCount, int colCount, Scanner sc) {
        char[][] matrix = new char[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            String[] elements = sc.nextLine().split("\\s+");
            for (int col = 0; col < colCount; col++) {
                matrix[row][col] = elements[col].charAt(0);
            }
        }

        return matrix;
    }
}
