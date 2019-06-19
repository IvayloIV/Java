package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Filter_by_Age {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer n = Integer.parseInt(bufferedReader.readLine());
        Map<String, Integer> people = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] personData = bufferedReader.readLine().split(", ");
            String name = personData[0];
            Integer age = Integer.parseInt(personData[1]);
            people.put(name, age);
        }

        String condition = bufferedReader.readLine();
        Integer age = Integer.parseInt(bufferedReader.readLine());
        String format = bufferedReader.readLine();

        BiPredicate<Map.Entry<String, Integer>, Integer> ageFilter = GetAgeFilter(condition);
        Consumer<Map.Entry<String, Integer>> printType = GetPrintType(format);

        people.entrySet().stream()
                .filter(p -> ageFilter.test(p, age))
                .forEach(printType);
    }

    private static Consumer<Map.Entry<String, Integer>> GetPrintType(String format) {
        Consumer<Map.Entry<String, Integer>> printByName = p -> System.out.println(p.getKey());
        Consumer<Map.Entry<String, Integer>> printByAge = p -> System.out.println(p.getValue());
        Consumer<Map.Entry<String, Integer>> printByNameAndAge = p -> System.out.printf("%s - %d%n", p.getKey(), p.getValue());

        if (format.equals("name")) {
            return printByName;
        } else if (format.equals("age")) {
            return printByAge;
        }
        return printByNameAndAge;
    }

    private static BiPredicate<Map.Entry<String, Integer>, Integer> GetAgeFilter(String condition) {
        BiPredicate<Map.Entry<String, Integer>, Integer> youngerThan = (person, n) -> person.getValue() <= n;
        BiPredicate<Map.Entry<String, Integer>, Integer> olderThan = (person, n) -> person.getValue() >= n;

        if (condition.equals("younger")) {
            return youngerThan;
        }
        return olderThan;
    }
}
