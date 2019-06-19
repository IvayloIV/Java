package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Positions_Of {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowCount = tokens[0];
        int colCount = tokens[1];
        int[][] matrix = new int[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Integer searchNum = Integer.parseInt(sc.nextLine());
        boolean isFound = false;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (matrix[row][col] == searchNum) {
                    isFound = true;
                    System.out.println(String.format("%d %d", row, col));
                }
            }
        }

        if (!isFound) {
            System.out.println("not found");
        }
    }
}
