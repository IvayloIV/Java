package Vehicles_Extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] carTokens = bufferedReader.readLine().split("\\s+");
        String[] trunkTokens = bufferedReader.readLine().split("\\s+");
        String[] busTokens = bufferedReader.readLine().split("\\s+");

        Vehicle car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]), Double.parseDouble(carTokens[3]));
        Vehicle truck = new Truck(Double.parseDouble(trunkTokens[1]), Double.parseDouble(trunkTokens[2]), Double.parseDouble(trunkTokens[3]));
        Vehicle bus = new Bus(Double.parseDouble(busTokens[1]), Double.parseDouble(busTokens[2]), Double.parseDouble(busTokens[3]));

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            Vehicle vehicle;

            if (tokens[1].equals("Car")) {
                vehicle = car;
            } else if (tokens[1].equals("Truck")) {
                vehicle = truck;
            } else {
                vehicle = bus;
            }

            try {
                if (tokens[0].equals("Refuel")) {
                    vehicle.refuel(Double.parseDouble(tokens[2]));
                } else {
                    if (tokens[0].equals("DriveEmpty") && vehicle instanceof Bus) {
                        Bus current = (Bus)vehicle;
                        current.driveEmpty(Double.parseDouble(tokens[2]));
                    } else {
                        vehicle.drive(Double.parseDouble(tokens[2]));
                    }

                    DecimalFormat format = new DecimalFormat("#.##");
                    System.out.println(String.format("%s travelled %s km",
                            tokens[1],
                            format.format(Double.parseDouble(tokens[2]))));
                }
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }

        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
