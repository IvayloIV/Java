package com.company.Car_Constructors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Car> cars = new ArrayList<>();
        Integer n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String make = tokens[0];

            Car car;
            if (tokens.length > 1) {
                String model = tokens[1];
                int horsePower = Integer.parseInt(tokens[2]);
                car = new Car(make, model, horsePower);
            } else {
                car = new Car(make);
            }

            cars.add(car);
        }

        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
