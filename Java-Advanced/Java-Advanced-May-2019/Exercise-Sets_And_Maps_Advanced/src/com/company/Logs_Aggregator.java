package com.company;

import java.util.*;

public class Logs_Aggregator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());
        Map<String, Integer> usersDuration = new TreeMap<>();
        Map<String, Set<String>> usersIp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String ip = tokens[0];
            String user = tokens[1];
            Integer duration = Integer.parseInt(tokens[2]);

            usersDuration.putIfAbsent(user, 0);
            usersDuration.put(user, usersDuration.get(user) + duration);

            usersIp.putIfAbsent(user, new TreeSet<>());
            usersIp.get(user).add(ip);
        }

        usersDuration.forEach((user, duration) -> {
            Set<String> userIps = usersIp.get(user);
            System.out.println(String.format("%s: %d [%s]", user, duration, String.join(", ", userIps)));
        });
    }
}
