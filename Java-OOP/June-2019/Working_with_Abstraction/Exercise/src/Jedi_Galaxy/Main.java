package Jedi_Galaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = createMatrix(scanner);

        String command;
        long sum = 0;
        while (!(command = scanner.nextLine()).equals("Let the Force be with you")) {
            int[] ivoPoint = convertInputToArr(command);
            int[] evilPoint = convertInputToArr(scanner.nextLine());

            evilPath(matrix, evilPoint);
            sum = getIvoSum(matrix, sum, ivoPoint);
        }

        System.out.println(sum);
    }

    private static long getIvoSum(int[][] matrix, long sum, int[] ivoPoint) {
        int ivoX = ivoPoint[0];
        int ivoY = ivoPoint[1];

        while (ivoX >= 0 && ivoY < matrix[1].length) {
            if (ivoX < matrix.length && ivoY >= 0 && ivoY < matrix[0].length) {
                sum += matrix[ivoX][ivoY];
            }

            ivoY++;
            ivoX--;
        }
        return sum;
    }

    private static void evilPath(int[][] matrix, int[] evilPoint) {
        int evilX = evilPoint[0];
        int evilY = evilPoint[1];

        while (evilX >= 0 && evilY >= 0) {
            if (evilX < matrix.length && evilY < matrix[0].length) {
                matrix[evilX][evilY] = 0;
            }
            evilX--;
            evilY--;
        }
    }

    private static int[][] createMatrix(Scanner scanner) {
        int[] dimestions = convertInputToArr(scanner.nextLine());
        int x = dimestions[0];
        int y = dimestions[1];

        int[][] matrix = new int[x][y];
        fillMatrix(x, y, matrix);
        return matrix;
    }

    private static void fillMatrix(int x, int y, int[][] matrix) {
        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }
        }
    }

    private static int[] convertInputToArr(String line) {
        return Arrays.stream(line
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
