package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class A_Miner_Task {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Long> resources = new LinkedHashMap<>();
        String resource;
        while (!"stop".equals(resource = sc.nextLine())) {
            Long quantity = Long.parseLong(sc.nextLine());
            resources.putIfAbsent(resource, 0L);
            resources.put(resource, resources.get(resource) + quantity);
        }

        resources.forEach((r, q) ->
                System.out.println(String.join(System.lineSeparator(), String.format("%s -> %s", r, q))));
    }
}
