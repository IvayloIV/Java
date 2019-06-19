package Cat_Lady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Cat> cats = new HashMap<>();
        String line;
        while (!"End".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");
            String type = tokens[0];
            String name = tokens[1];
            double data = Double.parseDouble(tokens[2]);

            Cat cat;
            switch (type) {
                case "Cymric":
                    cat = new Cymric(name, data);
                    break;
                case "Siamese":
                    cat = new Siamese(name, data);
                    break;
                default:
                    cat = new StreetExtraordinaire(name, data);
                    break;
            }

            cats.putIfAbsent(name, cat);
        }

        String catName = bufferedReader.readLine();
        System.out.println(cats.get(catName));
    }
}
