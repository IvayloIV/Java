package com.company;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parking_Lot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> carNumbers = new HashSet<>();
        String line;
        while (!"END".equals(line = sc.nextLine())) {
            String[] tokens = line.split(",\\s+");
            String command = tokens[0];
            String carNumber = tokens[1];

            if (command.equals("IN")) {
                carNumbers.add(carNumber);
            } else if (command.equals("OUT")) {
                carNumbers.remove(carNumber);
            }
        }

        if (carNumbers.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            System.out.println(String.join(System.lineSeparator(), carNumbers));
        }
    }
}
