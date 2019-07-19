package Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = bufferedReader.readLine()).equals("Beast!")) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            try {
                if (tokens.length < 3) {
                    throw new IllegalArgumentException("Invalid input!");
                }

                Animal animal = AnimalFactory.createAnimal(input, tokens);
                System.out.println(animal);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
