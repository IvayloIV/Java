import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Space_Station_Establishment {
    private static int currentRow;
    private static int currentCol;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        char[][] matrix = new char[n][];
        readMatrix(matrix, n, bufferedReader);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'S') {
                    currentRow = row;
                    currentCol = col;
                    break;
                }
            }
        }

        int starsPower = 0;
        while (true) {
            matrix[currentRow][currentCol] = '-';
            String command = bufferedReader.readLine();

            switch (command) {
                case "right":
                    currentCol++;
                    break;
                case "left":
                    currentCol--;
                    break;
                case "up":
                    currentRow--;
                    break;
                case "down":
                    currentRow++;
                    break;
            }

            if (IsOutOfMatrix(matrix, currentRow, currentCol)) {
                System.out.println("Bad news, the spaceship went to the void.");
                break;
            }

            char currentSymbol = matrix[currentRow][currentCol];
            if (Character.isDigit(currentSymbol)) {
                starsPower += Integer.parseInt(String.valueOf(currentSymbol));
            } else if (currentSymbol == 'O') {
                matrix[currentRow][currentCol] = '-';
                OUTER_LOOP: for (int row = 0; row < matrix.length; row++) {
                    for (int col = 0; col < matrix[row].length; col++) {
                        if (matrix[row][col] == 'O' && (currentRow != row || currentCol != col)) {
                            currentRow = row;
                            currentCol = col;
                            break OUTER_LOOP;
                        }
                    }
                }
            }

            matrix[currentRow][currentCol] = 'S';
            if (starsPower >= 50) {
                System.out.println("Good news! Stephen succeeded in collecting enough star power!");
                break;
            }
        }

        System.out.println(String.format("Star power collected: %s", starsPower));
        PrintMatrix(matrix);
    }

    private static void PrintMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean IsOutOfMatrix(char[][] matrix, int currentRow, int currentCol) {
        return currentRow < 0 ||
            currentCol < 0 ||
            currentRow > matrix.length - 1 ||
            currentCol > matrix[currentRow].length - 1;
    }

    private static void readMatrix(char[][] matrix, int n, BufferedReader bufferedReader) throws IOException {
        for (int i = 0; i < n; i++) {
            matrix[i] = bufferedReader.readLine().toCharArray();
        }
    }
}
