import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TronRacers {
    static int fRow = 0;
    static int fCol = 0;
    static int sRow = 0;
    static int sCol = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        char[][] matrix = new char[n][];
        readMatrix(n, matrix, bufferedReader);
        findSymbols(matrix);

        while (true) {
            String[] commands = bufferedReader.readLine().split("\\s+");
            String fCommand = commands[0];
            String sCommand = commands[1];

            if (!executeCommands(matrix, fCommand, sCommand)) {
                break;
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(row);
        }
    }

    private static boolean executeCommands(char[][] matrix, String fCommand, String sCommand) {
        boolean f = move(matrix, fCommand, 'f');

        if (!f) {
            return false;
        }

        return move(matrix, sCommand, 's');
    }

    private static boolean move(char[][] matrix, String command, char el) {
        int row = 0;
        int col = 0;
        if (command.equals("up")) {
            row--;
        } else if (command.equals("down")) {
            row++;
        } else if (command.equals("left")) {
            col--;
        } else if (command.equals("right")) {
            col++;
        }

        if (el == 'f') {
            fRow = transformNum(fRow + row, matrix.length);
            fCol = transformNum(fCol + col, matrix.length);
            return checkValid(matrix, el);
        } else {
            sRow = transformNum(sRow + row, matrix.length);
            sCol = transformNum(sCol + col, matrix.length);
            return checkValid(matrix, el);
        }
    }

    private static boolean checkValid(char[][] matrix, char el) {
        if (el == 'f') {
            if (matrix[fRow][fCol] == 's') {
                matrix[fRow][fCol] = 'x';
                return false;
            } else {
                matrix[fRow][fCol] = 'f';
                return true;
            }
        } else {
            if (matrix[sRow][sCol] == 'f') {
                matrix[sRow][sCol] = 'x';
                return false;
            } else {
                matrix[sRow][sCol] = 's';
                return true;
            }
        }
    }

    private static int transformNum(int sum, int length) {
        if (sum < 0) {
            return length - 1;
        }

        return sum % length;
    }

    private static void findSymbols(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'f') {
                    fRow = row;
                    fCol = col;
                } else if (matrix[row][col] == 's') {
                    sRow = row;
                    sCol = col;
                }
            }
        }
    }

    private static void readMatrix(int n, char[][] matrix, BufferedReader br) throws IOException {
        for (int i = 0; i < n; i++) {
            char[] elements = br.readLine().toCharArray();
            matrix[i] = elements;
        }
    }
}
