package Car_Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Engine> engines = new HashMap<>();
        addEngines(bufferedReader, engines);

        List<Car> cars = new ArrayList<>();
        addCars(bufferedReader, engines, cars);

        printCars(cars);
    }

    private static void printCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static void addCars(BufferedReader bufferedReader,
                                Map<String, Engine> engines,
                                List<Car> cars) throws IOException {
        Integer carsCount = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < carsCount; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String model = tokens[0];
            Engine engine = engines.get(tokens[1]);

            Car car;
            if (tokens.length == 2) {
                car = new Car(model, engine);
            } else if (tokens.length == 4) {
                String weight = tokens[2];
                String color = tokens[3];
                car = new Car(model, engine, weight, color);
            } else {
                String data = tokens[2];

                if (data.matches("^\\d+$")) {
                    Integer weight = Integer.parseInt(data);
                    car = new Car(model, engine, weight);
                } else {
                    car = new Car(model, engine, data);
                }
            }

            cars.add(car);
        }
    }

    private static void addEngines(BufferedReader bufferedReader,
                                   Map<String, Engine> engines) throws IOException {
        int enginesCount = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < enginesCount; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String model = tokens[0];
            double power = Double.parseDouble(tokens[1]);

            Engine engine;
            if (tokens.length == 2) {
                engine = new Engine(model, power);
            } else if (tokens.length == 4) {
                String displacement = tokens[2];
                String efficiency = tokens[3];
                engine = new Engine(model, power, displacement, efficiency);
            } else {
                String data = tokens[2];

                if (data.matches("^\\d+$")) {
                    Integer displacement = Integer.parseInt(data);
                    engine = new Engine(model, power, displacement);
                } else {
                    engine = new Engine(model, power, data);
                }
            }

            engines.put(model, engine);
        }
    }
}
