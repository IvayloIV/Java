package models.players;

import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;

public abstract class PlayerFactory {
    public static Player createPlayer(String type, String username) {
        switch (type) {
            case "Beginner":
                return new Beginner(new CardRepositoryImpl(), username);
            case "Advanced":
                return new Advanced(new CardRepositoryImpl(), username);
            default:
                throw new IllegalArgumentException("Player type not exist.");
        }
    }
}
