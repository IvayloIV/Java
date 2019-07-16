package Hotel_Reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = bufferedReader.readLine().split("\\s+");
        double price = Double.parseDouble(tokens[0]);
        int numOfDays = Integer.parseInt(tokens[1]);
        String season = tokens[2];
        String discount = tokens[3];

        price *= numOfDays * Season.valueOf(season).getValue();
        price -= price * Discount.valueOf(discount).getValue() / 100;

        System.out.printf("%.2f", price);
    }
}
