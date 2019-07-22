package Animal_Farm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        int age = Integer.parseInt(bufferedReader.readLine());

        try {
            Chicken chicken = new Chicken(name, age);
            System.out.println(chicken);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }
}
