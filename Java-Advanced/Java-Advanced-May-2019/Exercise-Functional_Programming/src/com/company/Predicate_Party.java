package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Predicate_Party {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<String, String> isStartWith = (name, start) -> name.startsWith(start);
        BiPredicate<String, String> isEndWith = (name, start) -> name.endsWith(start);
        BiPredicate<String, String> equalLength = (name, length) -> name.length() == Integer.parseInt(length);

        List<String> names = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .collect(Collectors.toList());

        String line;
        while(!"Party!".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String operationType = tokens[1];
            String data = tokens[2];

            BiPredicate<String, String> operation;
            if (operationType.equals("StartsWith")) {
                operation = isStartWith;
            } else if (operationType.equals("EndsWith")) {
                operation = isEndWith;
            } else {
                operation = equalLength;
            }

            if (command.equals("Remove")) {
                names.removeIf(a -> operation.test(a, data));
            } else if (command.equals("Double")) {
                for (int i = names.size() - 1; i >= 0; i--) {
                    String name = names.get(i);
                    if (operation.test(name, data)) {
                        names.add(name);
                    }
                }
            }
        }

        if (names.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            names = names.stream().sorted().collect(Collectors.toList());
            System.out.println(String.join(", ", names) + " are going to the party!");
        }
    }
}
