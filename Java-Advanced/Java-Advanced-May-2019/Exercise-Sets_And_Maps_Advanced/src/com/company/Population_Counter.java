package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Population_Counter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Map<String, Long>> countries = new LinkedHashMap<>();

        String input;
        while (!"report".equals(input = sc.nextLine())) {
            String[] tokens = input.split("\\|");
            String city = tokens[0];
            String country = tokens[1];
            Long population = Long.parseLong(tokens[2]);

            countries.putIfAbsent(country, new LinkedHashMap<>());
            countries.get(country).putIfAbsent(city, 0L);
            countries.get(country).put(city, countries.get(country).get(city) + population);
        }

        countries.entrySet().stream().sorted((a, b) -> {
           Long aSum = a.getValue().entrySet().stream().mapToLong(Map.Entry::getValue).sum();
           Long bSum = b.getValue().entrySet().stream().mapToLong(Map.Entry::getValue).sum();

           return bSum.compareTo(aSum);
        }).forEach(entry -> {
            Long totalPopulation = entry.getValue().entrySet().stream().mapToLong(Map.Entry::getValue).sum();
            System.out.println(String.format("%s (total population: %d)", entry.getKey(), totalPopulation));

            entry.getValue().entrySet().stream().sorted((a, b) -> {
                return b.getValue().compareTo(a.getValue());
            }).forEach(kvp -> {
                System.out.println(String.format("=>%s: %d", kvp.getKey(), kvp.getValue()));
            });
        });
    }
}
