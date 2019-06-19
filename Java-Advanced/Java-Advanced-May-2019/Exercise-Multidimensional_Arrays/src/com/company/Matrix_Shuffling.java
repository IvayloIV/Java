package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix_Shuffling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rowCount = tokens[0];
        int colCount = tokens[1];

        String[][] matrix = new String[rowCount][];

        for (int row = 0; row < rowCount; row++) {
            matrix[row] = sc.nextLine().split("\\s+");
        }

        String command;
        while (!"END".equals(command = sc.nextLine())) {
            try {
                String[] elements = command.split("\\s+");

                if (elements.length != 5 || !elements[0].equals("swap")) {
                    throw new Exception("Invalid input!");
                }

                Integer row1 = Integer.parseInt(elements[1]);
                Integer col1 = Integer.parseInt(elements[2]);
                Integer row2 = Integer.parseInt(elements[3]);
                Integer col2 = Integer.parseInt(elements[4]);

                if (!ValidateCoordinate(matrix, row1, col1) || !ValidateCoordinate(matrix, row2, col2)) {
                    throw new Exception("Invalid input!");
                }

                SwapElements(matrix, row1, col1, row2, col2);
                PrintMatrix(matrix);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void PrintMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row).replaceAll("[]\\[,]", ""));
        }
    }

    private static void SwapElements(String[][] matrix, Integer row1, Integer col1, Integer row2, Integer col2) {
        String temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }

    private static boolean ValidateCoordinate(String[][] matrix, Integer row, Integer col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
    }
}
