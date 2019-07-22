package Pizza_Calories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] pizzaTokens = bufferedReader.readLine().split("\\s+");
            Pizza pizza = new Pizza(pizzaTokens[1], Integer.parseInt(pizzaTokens[2]));

            String[] doughTokens = bufferedReader.readLine().split("\\s+");
            Dough dough = new Dough(doughTokens[1], doughTokens[2], Double.parseDouble(doughTokens[3]));
            pizza.setDough(dough);

            String input;
            while (!(input = bufferedReader.readLine()).equals("END")) {
                String[] toppingTokens = input.split("\\s+");
                Topping topping = new Topping(toppingTokens[1],
                        Double.parseDouble(toppingTokens[2]));

                pizza.addTopping(topping);
            }

            System.out.println(pizza);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }
}
