package Rhombus_of_Stars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 1; i <= n; i++) {
            printPattern(n - i, " ");
            printPattern(i, "* ");
            System.out.println();
        }

        for (int i = n - 1; i >= 1; i--) {
            printPattern(n - i, " ");
            printPattern(i, "* ");
            System.out.println();
        }
    }

    public static void printPattern(int n, String pattern) {
        for (int i = 0; i < n; i++) {
            System.out.print(pattern);
        }
    }
}