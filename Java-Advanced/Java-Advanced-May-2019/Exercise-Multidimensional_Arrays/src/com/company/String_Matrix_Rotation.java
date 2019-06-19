package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class String_Matrix_Rotation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer rotation = Integer.parseInt(sc.nextLine().split("[()]")[1]) % 360;
        Integer colCount = 0;
        ArrayList<String> elements = new ArrayList<>();
        String line;
        while (!"END".equals(line = sc.nextLine())) {
            if (line.length() > colCount) {
                colCount = line.length();
            }
            
            elements.add(line);
        }
        
        char[][] matrix = new char[elements.size()][colCount];
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            for (int j = 0; j < element.length(); j++) {
                matrix[i][j] = element.charAt(j);
            }
        }

        while (rotation > 0) {
            matrix = RotateMatrix(matrix);
            rotation -= 90;
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '\u0000') {
                    System.out.print(" ");
                } else {
                    System.out.print(matrix[row][col]);
                }
            }
            System.out.println();
        }
    }

    private static char[][] RotateMatrix(char[][] matrix) {
        char[][] rotatedMatrix = new char[matrix[0].length][matrix.length];

        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int col = 0; col < matrix[row].length; col++) {
                rotatedMatrix[col][matrix.length - 1 - row] = matrix[row][col];
            }
        }

        return rotatedMatrix;
    }
}
