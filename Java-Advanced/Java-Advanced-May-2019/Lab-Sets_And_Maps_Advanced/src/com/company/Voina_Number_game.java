package com.company;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Voina_Number_game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Integer> firstPlayerCards = ReadPlayerCards(sc.nextLine());
        Set<Integer> secondPlayerCards = ReadPlayerCards(sc.nextLine());

        for (int i = 0; i < 50; i++) {
            if (firstPlayerCards.isEmpty() || secondPlayerCards.isEmpty()) {
                break;
            }

            int firstPlayerCard = firstPlayerCards.iterator().next();
            firstPlayerCards.remove(firstPlayerCard);
            int secondPlayerCard = secondPlayerCards.iterator().next();
            secondPlayerCards.remove(secondPlayerCard);

            if (firstPlayerCard > secondPlayerCard) {
                firstPlayerCards.add(firstPlayerCard);
                firstPlayerCards.add(secondPlayerCard);
            } else if (firstPlayerCard < secondPlayerCard) {
                secondPlayerCards.add(firstPlayerCard);
                secondPlayerCards.add(secondPlayerCard);
            } else {
                firstPlayerCards.add(firstPlayerCard);
                secondPlayerCards.add(secondPlayerCard);
            }
        }

        if (firstPlayerCards.size() > secondPlayerCards.size()) {
            System.out.println("First player win!");
        } else if (firstPlayerCards.size() < secondPlayerCards.size()) {
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }

    private static Set<Integer> ReadPlayerCards(String line) {
        Set<Integer> playerCards = new LinkedHashSet<>();
        Arrays.stream(line.split("\\s+")).forEach((c) -> playerCards.add(Integer.parseInt(c)));
        return  playerCards;
    }
}
