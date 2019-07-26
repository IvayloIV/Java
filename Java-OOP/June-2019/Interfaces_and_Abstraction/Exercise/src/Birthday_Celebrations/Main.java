package Birthday_Celebrations;

import Birthday_Celebrations.contracts.Birthable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Birthable> birthables = new ArrayList<>();
        String input;
        while (!(input = bufferedReader.readLine()).equals("End")) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];

            if (type.equals("Citizen")) {
                birthables.add(new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
            } else if (type.equals("Pet")) {
                birthables.add(new Pet(tokens[1], tokens[2]));
            }
        }

        String data = bufferedReader.readLine();
        for (Birthable birthable : birthables) {
            String[] tokens = birthable.getBirthDate().split("/");
            String year = tokens[2];
            if (data.equals(year)) {
                System.out.println(birthable.getBirthDate());
            }
        }
    }
}
