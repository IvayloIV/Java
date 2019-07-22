package Shopping_Spree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Person> persons = new LinkedHashMap<>();
        LinkedHashMap<String, Product> products = new LinkedHashMap<>();

        try {
            String[] personMoney = bufferedReader.readLine().split(";");
            for (int i = 0; i < personMoney.length; i++) {
                String[] tokens = personMoney[i].split("=");
                String name = tokens[0];
                double money = Double.parseDouble(tokens[1]);

                persons.putIfAbsent(name, new Person(name, money));
            }

            String[] productCost = bufferedReader.readLine().split(";");
            for (int i = 0; i < productCost.length; i++) {
                String[] tokens = productCost[i].split("=");
                String name = tokens[0];
                double cost = Double.parseDouble(tokens[1]);

                products.put(name, new Product(name, cost));
            }

            String input;
            while (!(input = bufferedReader.readLine()).equals("END")) {
                String[] tokens = input.split("\\s+");
                String personName = tokens[0];
                String productName = tokens[1];

                Product product = products.get(productName);
                try {
                    persons.get(personName).buyProduct(product);
                    System.out.println(String.format("%s bought %s", personName, productName));
                } catch (IllegalArgumentException err) {
                    System.out.println(err.getMessage());
                }
            }

            for (Map.Entry<String, Person> entry : persons.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }
}
