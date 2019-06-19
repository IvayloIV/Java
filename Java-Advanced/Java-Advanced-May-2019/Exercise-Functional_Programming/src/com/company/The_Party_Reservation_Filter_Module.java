package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class The_Party_Reservation_Filter_Module {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] names = bufferedReader.readLine().split("\\s+");
        Map<String, Predicate<String>> hashmap = new HashMap<>();

        String line;
        while (!"Print".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String filterType = tokens[1];
            String filterParam = tokens[2];

            String filterCommand = String.format("%s;%s", filterType, filterParam);

            if (command.equals("Add filter")) {
                Predicate<String> filterPred = GetFilterPred(filterType, filterParam);
                hashmap.putIfAbsent(filterCommand, filterPred);
            } else {
                hashmap.remove(filterCommand);
            }
        }

        Arrays.stream(names).forEach((name) -> {
            boolean isValid = true;

            for (Map.Entry<String, Predicate<String>> kvp : hashmap.entrySet()) {
                if (kvp.getValue().test(name)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                System.out.print(name + " ");
            }
        });
    }

    private static Predicate<String> GetFilterPred(String filterType, String filterParam) {
        switch (filterType) {
            case "Starts with":
                return (el) -> el.startsWith(filterParam);
            case "Ends with":
                return (el) -> el.endsWith(filterParam);
            case "Contains":
                return (el) -> el.contains(filterParam);
            default:
                return (el) -> Integer.parseInt(filterParam) == el.length();
        }
    }
}
