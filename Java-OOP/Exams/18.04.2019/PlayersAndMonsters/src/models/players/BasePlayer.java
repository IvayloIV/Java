package models.players;

import common.ConstantMessages;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player {
    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String username, int health) {
        this.setUsername(username);
        this.setHealth(health);
        this.cardRepository = cardRepository;
    }

    @Override
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string.");
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero.");
        }
        this.health = health;
    }

    @Override
    public CardRepository getCardRepository() {
        return cardRepository;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }

        if (this.getHealth() - damagePoints <= 0) {
            this.setHealth(0);
            this.isDead = true;
        } else {
            this.setHealth(this.getHealth() - damagePoints);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(ConstantMessages.PLAYER_REPORT_INFO,
                this.getUsername(),
                this.getHealth(),
                this.cardRepository.getCount()))
            .append(System.lineSeparator());

        for (Card card : this.getCardRepository().getCards()) {
            stringBuilder.append(card)
                .append(System.lineSeparator());
        }

        stringBuilder.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
        return stringBuilder.toString().trim();
    }
}
