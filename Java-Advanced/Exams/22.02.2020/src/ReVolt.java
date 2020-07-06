import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class ReVolt {

    private static int positionRow = 0;
    private static int positionCol = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int matrixRow = Integer.parseInt(br.readLine());
        int commandsCount = Integer.parseInt(br.readLine());
        Consumer<String> printTotalResult = System.out::println;

        char[][] matrix = createMatrix(br, matrixRow);

        matrix[positionRow][positionCol] = '-';
        boolean isFound = false;
        for (int i = 0; i < commandsCount; i++) {
            String command = br.readLine();
            int nextRow = 0;
            int nextCol = 0;

            switch (command) {
                case "down":
                    nextRow += 1;
                    break;
                case "up":
                    nextRow -= 1;
                    break;
                case "left":
                    nextCol -= 1;
                    break;
                case "right":
                    nextCol += 1;
                    break;
            }

            positionRow += nextRow;
            positionCol += nextCol;

            validateSize(matrix);

            if (matrix[positionRow][positionCol] == 'T') {
                positionRow -= nextRow;
                positionCol -= nextCol;
            } else if (matrix[positionRow][positionCol] == 'B') {
                positionRow += nextRow;
                positionCol += nextCol;
            }

            validateSize(matrix);

            if (matrix[positionRow][positionCol] == 'F') {
                printTotalResult.accept("Player won!");
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            printTotalResult.accept("Player lost!");
        }
        matrix[positionRow][positionCol] = 'f';
        printMatrix(matrix);
    }

    private static void validateSize(char[][] matrix) {
        if (positionRow == -1) {
            positionRow = matrix.length - 1;
        }

        if (positionRow > matrix.length - 1) {
            positionRow = 0;
        }

        if (positionCol == -1) {
            positionCol = matrix[positionRow].length - 1;
        }

        if (positionCol > matrix[positionRow].length - 1) {
            positionCol = 0;
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static char[][] createMatrix(BufferedReader br, int matrixRow) throws IOException {
        char[][] matrix = new char[matrixRow][];
        for (int row = 0; row < matrixRow; row++) {
            String currentCol = br.readLine();
            matrix[row] = new char[currentCol.length()];
            for (int col = 0; col < currentCol.length(); col++) {
                matrix[row][col] = currentCol.charAt(col);
                if (matrix[row][col] == 'f') {
                    positionRow = row;
                    positionCol = col;
                }
            }
        }
        return matrix;
    }
}
