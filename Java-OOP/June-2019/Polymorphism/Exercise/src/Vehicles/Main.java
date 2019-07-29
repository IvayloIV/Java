package Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] carTokens = bufferedReader.readLine().split("\\s+");
        String[] trunkTokens = bufferedReader.readLine().split("\\s+");

        Vehicle car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]));
        Vehicle truck = new Truck(Double.parseDouble(trunkTokens[1]), Double.parseDouble(trunkTokens[2]));

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            Vehicle vehicle;

            if (tokens[1].equals("Car")) {
                vehicle = car;
            } else {
                vehicle = truck;
            }

            try {
                if (tokens[0].equals("Drive")) {
                    vehicle.drive(Double.parseDouble(tokens[2]));
                    DecimalFormat format = new DecimalFormat("#.##");
                    System.out.println(String.format("%s travelled %s km",
                            tokens[1],
                            format.format(Double.parseDouble(tokens[2]))));
                } else {
                    vehicle.refuel(Double.parseDouble(tokens[2]));
                }
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }

        System.out.println(car);
        System.out.println(truck);
    }
}
