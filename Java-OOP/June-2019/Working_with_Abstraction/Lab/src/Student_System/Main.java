package Student_System;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        String[] input;
        while (!(input = scanner.nextLine().split(" "))[0].equals("Exit")) {
            studentSystem.parseCommand(input);
        }
    }
}
