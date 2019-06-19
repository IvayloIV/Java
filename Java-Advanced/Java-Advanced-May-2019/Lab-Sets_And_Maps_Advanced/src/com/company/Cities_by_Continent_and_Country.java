package com.company;

import java.util.*;

public class Cities_by_Continent_and_Country {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Map<String, List<String>>> continents = new LinkedHashMap<>();
        Integer n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split("\\s+");
            String continent = line[0];
            String country = line[1];
            String city = line[2];

            continents.putIfAbsent(continent, new LinkedHashMap<>());
            continents.get(continent).putIfAbsent(country, new ArrayList<>());
            continents.get(continent).get(country).add(city);
        }

        continents.forEach((continent, countries) -> {
            System.out.println(continent + ":");
            countries.forEach((country, cities) -> {
                String citiesJoin = String.join(", ", cities);
                System.out.println(String.format("  %s -> %s", country, citiesJoin));
            });
        });
    }
}
