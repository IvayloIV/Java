package com.company;

import java.util.*;

public class Hands_Of_Cards {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Set<String>> players = new LinkedHashMap<>();
        Map<String, Integer> faces = new HashMap<String, Integer>() {{
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("A", 14);
        }};
        Map<String, Integer> suits = new HashMap<String, Integer>() {{
            put("S", 4);
            put("H", 3);
            put("D", 2);
            put("C", 1);
        }};


        String input;
        while (!"JOKER".equals(input = sc.nextLine())) {
            String[] tokens = input.split(":\\s+");
            String playerName = tokens[0];
            List<String> cards = Arrays.asList(tokens[1].split(",\\s+"));

            players.putIfAbsent(playerName, new LinkedHashSet<>());
            players.get(playerName).addAll(cards);
        }

        players.forEach((name, card) -> {
            Integer points = 0;
            for (String c : card) {
                String face = c.substring(0, c.length() - 1);
                String suit = c.substring(c.length() - 1);

                Integer facePoints = 0;
                if (faces.containsKey(face)) {
                    facePoints += faces.get(face);
                } else {
                    facePoints += Integer.parseInt(face);
                }

                points += facePoints * suits.get(suit);
            }

            System.out.println(String.format("%s: %d", name, points));
        });
    }
}
