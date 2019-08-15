package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Beginner;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }
        checkForBeginner(attackPlayer);
        checkForBeginner(enemyPlayer);

        addBonusHealth(attackPlayer);
        addBonusHealth(enemyPlayer);


        int attackerDamage = getPlayerDamage(attackPlayer);
        int enemyDamage = getPlayerDamage(enemyPlayer);
        int count = 0;
        while (!attackPlayer.isDead() && !enemyPlayer.isDead()) {
            if (count % 2 == 0) {
                enemyPlayer.takeDamage(attackerDamage);
            } else {
                attackPlayer.takeDamage(enemyDamage);
            }
            count++;
        }
    }

    private int getPlayerDamage(Player player) {
        int damage = 0;
        for (Card card : player.getCardRepository().getCards()) {
            damage += card.getDamagePoints();
        }

        return damage;
    }

    private void addBonusHealth(Player player) {
        for (Card card : player.getCardRepository().getCards()) {
            player.setHealth(player.getHealth() + card.getHealthPoints());
        }
    }

    private void checkForBeginner(Player player) {
        if (player instanceof Beginner) {
            player.setHealth(player.getHealth() + 40);

            for (Card card : player.getCardRepository().getCards()) {
                card.setDamagePoints(card.getDamagePoints() + 30);
            }
        }
    }
}
