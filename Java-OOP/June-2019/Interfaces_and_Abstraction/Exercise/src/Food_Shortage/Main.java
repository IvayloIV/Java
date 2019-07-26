package Food_Shortage;

import Food_Shortage.contracts.Buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Buyer> buyers = new HashMap<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        readBuyers(bufferedReader, buyers, n);

        buyFood(bufferedReader, buyers);
        int sumOfFood = getSumOfFood(buyers);
        System.out.println(sumOfFood);
    }

    private static void readBuyers(BufferedReader bufferedReader, HashMap<String, Buyer> buyers, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String name = tokens[0];
            Buyer buyer;

            if (tokens.length == 4) {
                buyer = new Citizen(name, Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
            } else {
                buyer = new Rebel(name, Integer.parseInt(tokens[1]), tokens[2]);
            }

            buyers.putIfAbsent(name, buyer);
        }
    }

    private static void buyFood(BufferedReader bufferedReader, HashMap<String, Buyer> buyers) throws IOException {
        String input;
        while (!(input = bufferedReader.readLine()).equals("End")) {
            if (buyers.containsKey(input)) {
                buyers.get(input).buyFood();
            }
        }
    }

    private static int getSumOfFood(HashMap<String, Buyer> buyers) {
        int sumOfFood = 0;
        for (Map.Entry<String, Buyer> kvp : buyers.entrySet()) {
            sumOfFood += kvp.getValue().getFood();
        }
        return sumOfFood;
    }
}
