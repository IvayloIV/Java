package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Crossfire {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rowCount = tokens[0];
        int colCount = tokens[1];
        List<List<Integer>> matrix = new ArrayList<>();

        int count = 1;
        for (int row = 0; row < rowCount; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < colCount; col++) {
                matrix.get(row).add(count++);
            }
        }

        String line;
        while (!"Nuke it from orbit".equals(line = sc.nextLine())) {
            int[] elements = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int row = elements[0];
            int col = elements[1];
            int range = elements[2];

            RemoveElements(matrix, row, col, range);
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            for (Integer el : row) {
                System.out.print(el + " ");
            }

            System.out.println();
        }
    }

    private static void RemoveElements(List<List<Integer>> matrix, int rowStart, int colStart, int range) {
        for (int row = rowStart - range; row <= rowStart + range; row++) {
            if (isInside(matrix, row, colStart) && row != rowStart) {
                matrix.get(row).remove(colStart);
            }
        }

        for (int col = colStart + range; col >= colStart - range; col--) {
            if (isInside(matrix, rowStart, col)) {
                matrix.get(rowStart).remove(col);
            }
        }

        matrix.removeIf(List::isEmpty);
    }

    private static boolean isInside(List<List<Integer>> matrix, int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.size() && col < matrix.get(row).size();
    }
}
