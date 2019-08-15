package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    private List<Card> cards;

    public CardRepositoryImpl() {
        this.cards = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public void add(Card card) {
        this.checkForNullCard(card);

        if (this.cards.stream().anyMatch(c -> c.getName().equals(card.getName()))) {
            throw new IllegalArgumentException(String.format("Card %s already exists!",
                    card.getName()));
        }

        this.cards.add(card);
    }

    @Override
    public boolean remove(Card card) {
        this.checkForNullCard(card);
        return this.cards.remove(card);
    }

    @Override
    public Card find(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        throw new IllegalArgumentException("Card cannot be null!");
    }

    private void checkForNullCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null!");
        }
    }
}
