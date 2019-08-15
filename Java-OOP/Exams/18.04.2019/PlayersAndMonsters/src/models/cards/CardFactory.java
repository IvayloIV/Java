package models.cards;

import models.cards.interfaces.Card;

public abstract class CardFactory {
    public static Card createCard(String type, String cardName) {
        switch (type) {
            case "Trap":
                return new TrapCard(cardName);
            case "Magic":
                return new MagicCard(cardName);
            default:
                throw new IllegalArgumentException("Card does not exist.");
        }
    }
}
