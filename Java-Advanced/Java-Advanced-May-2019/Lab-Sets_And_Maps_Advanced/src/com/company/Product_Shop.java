package com.company;

import java.util.*;

public class Product_Shop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Map<String, Double>> shops = new TreeMap<>();
        String input;
        while (!"Revision".equals(input = sc.nextLine())) {
            String[] tokens = input.split(",\\s+");
            String shop = tokens[0];
            String product = tokens[1];
            Double price = Double.parseDouble(tokens[2]);

            shops.putIfAbsent(shop, new LinkedHashMap<>());
            shops.get(shop).put(product, price);
        }

        shops.forEach((shop, products) -> {
            System.out.println(shop + "->");
            products.forEach((product, price) -> {
                System.out.println(String.format("Product: %s, Price: %.1f", product, price));
            });
        });
    }
}
