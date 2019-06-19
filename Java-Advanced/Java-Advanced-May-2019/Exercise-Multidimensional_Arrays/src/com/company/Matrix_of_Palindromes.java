package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix_of_Palindromes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowCount = tokens[0];
        int colCount = tokens[1];
        String[][] matrix = new String[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String firstLastLetter = String.valueOf((char)(97 + row));
                String middleLetter = String.valueOf((char)(97 + row + col));
                matrix[row][col] = firstLastLetter + middleLetter + firstLastLetter;
            }
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row).replaceAll("[]\\[,]", ""));
        }
    }
}
