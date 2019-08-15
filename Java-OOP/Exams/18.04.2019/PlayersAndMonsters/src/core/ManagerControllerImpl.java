package core;

import common.ConstantMessages;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.CardFactory;
import models.cards.interfaces.Card;
import models.players.PlayerFactory;
import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;
import repositories.CardRepositoryImpl;
import repositories.interfaces.PlayerRepository;
import repositories.PlayerRepositoryImpl;


public class ManagerControllerImpl implements ManagerController {
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        this.playerRepository = new PlayerRepositoryImpl();
        this.cardRepository = new CardRepositoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {
        Player player = PlayerFactory.createPlayer(type, username);
        playerRepository.add(player);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER,
                type,
                username);
    }

    @Override
    public String addCard(String type, String name) {
        Card card = CardFactory.createCard(type, name);
        this.cardRepository.add(card);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Card card = this.cardRepository.find(cardName);
        Player player = this.playerRepository.find(username);
        player.getCardRepository().add(card);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attackPlayer = this.playerRepository.find(attackUser);
        Player enemyPlayer = this.playerRepository.find(enemyUser);
        this.battlefield.fight(attackPlayer, enemyPlayer);

        return String.format(ConstantMessages.FIGHT_INFO, attackPlayer.getHealth(), enemyPlayer.getHealth());
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : this.playerRepository.getPlayers()) {
            stringBuilder.append(player)
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
