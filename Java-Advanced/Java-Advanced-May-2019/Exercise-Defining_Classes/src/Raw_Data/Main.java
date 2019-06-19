package Raw_Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Car> cars = new ArrayList<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");

            String model = tokens[0];
            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            double tyrePressure1 = Double.parseDouble(tokens[5]);
            int tyreAge1 = Integer.parseInt(tokens[6]);
            double tyrePressure2 = Double.parseDouble(tokens[7]);
            int tyreAge2 = Integer.parseInt(tokens[8]);
            double tyrePressure3 = Double.parseDouble(tokens[9]);
            int tyreAge3 = Integer.parseInt(tokens[10]);
            double tyrePressure4 = Double.parseDouble(tokens[11]);
            int tyreAge4 = Integer.parseInt(tokens[12]);

            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Car car = new Car(model, engine, cargo);

            car.addTire(new Tire(tyrePressure1, tyreAge1));
            car.addTire(new Tire(tyrePressure2, tyreAge2));
            car.addTire(new Tire(tyrePressure3, tyreAge3));
            car.addTire(new Tire(tyrePressure4, tyreAge4));
            cars.add(car);
        }

        String type = bufferedReader.readLine();
        cars.stream()
                .filter(a -> type.equals("fragile") ? a.isFragile() : a.isFlamable())
                .forEach(System.out::println);
    }
}
