import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Enter_Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int start = 0;
        int end = 0;

        while (true) {
            try {
                start = Integer.parseInt(bufferedReader.readLine());
                if (start <= 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid start number");
            }
        }

        while (true) {
            try {
                end = Integer.parseInt(bufferedReader.readLine());
                if (end > 100 || start >= end) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("End must be valid number bigger than start");
            }
        }

        printNumbers(start, end);
    }

    private static void printNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
