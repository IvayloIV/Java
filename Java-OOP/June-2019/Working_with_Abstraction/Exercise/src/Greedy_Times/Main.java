package Greedy_Times;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacity = Long.parseLong(scanner.nextLine());
        String[] tokens = scanner.nextLine().split("\\s+");

        LinkedHashMap<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();
        addItemsToBag(capacity, tokens, bag);
        printResult(bag);
    }

    private static void addItemsToBag(long capacity, String[] tokens, LinkedHashMap<String, LinkedHashMap<String, Long>> bag) {
        for (int i = 0; i < tokens.length; i += 2) {
            String name = tokens[i];
            long totalAmount = Long.parseLong(tokens[i + 1]);

            String type = getType(name);

            if (type.equals("") ||
                    capacity < bag.values()
                            .stream()
                            .map(Map::values)
                            .flatMap(Collection::stream)
                            .mapToLong(e -> e).sum() + totalAmount) {
                continue;
            }

            switch (type) {
                case "Gem":
                    if (isValid(bag, totalAmount, type, "Gold")) continue;
                    break;
                case "Cash":
                    if (isValid(bag, totalAmount, type, "Gem")) continue;
                    break;
            }

            if (!bag.containsKey(type)) {
                bag.put((type), new LinkedHashMap<>());
            }

            if (!bag.get(type).containsKey(name)) {
                bag.get(type).put(name, 0L);
            }
            bag.get(type).put(name, bag.get(type).get(name) + totalAmount);
        }
    }

    private static boolean isValid(LinkedHashMap<String, LinkedHashMap<String, Long>> bag, long totalAmount, String type, String gem) {
        if (!bag.containsKey(type)) {
            if (bag.containsKey(gem)) {
                return totalAmount > getSum(bag.get("Gold"));
            } else {
                return true;
            }
        }
        return getSum(bag.get(type)) + totalAmount > getSum(bag.get(gem));
    }

    private static void printResult(LinkedHashMap<String, LinkedHashMap<String, Long>> bag) {
        for (Map.Entry<String, LinkedHashMap<String, Long>> x : bag.entrySet()) {
            Long sumValues = getSum(x.getValue());

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue()
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static long getSum(LinkedHashMap<String, Long> stringLongLinkedHashMap) {
        return stringLongLinkedHashMap.values().stream().mapToLong(e -> e).sum();
    }

    private static String getType(String name) {
        if (name.length() == 3) {
            return "Cash";
        } else if (name.toLowerCase().endsWith("gem")) {
            return "Gem";
        } else if (name.toLowerCase().equals("gold")) {
            return"Gold";
        }
        return "";
    }
}