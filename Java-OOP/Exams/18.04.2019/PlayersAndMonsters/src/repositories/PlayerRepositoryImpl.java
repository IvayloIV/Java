package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.*;

public class PlayerRepositoryImpl implements PlayerRepository {
    private List<Player> players;

    public PlayerRepositoryImpl() {
        this.players = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void add(Player player) {
        this.checkForNullPlayer(player);

        if (checkPlayerNameExist(player.getUsername())) {
            throw new IllegalArgumentException(String.format("Player %s already exists!",
                    player.getUsername()));
        }

        this.players.add(player);
    }

    @Override
    public boolean remove(Player player) {
        this.checkForNullPlayer(player);
        return this.players.remove(player);
    }

    @Override
    public Player find(String name) {
        for (Player player : this.players) {
            if (player.getUsername().equals(name)) {
                return player;
            }
        }

        throw new IllegalArgumentException("Player cannot be null");
    }

    private void checkForNullPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
    }

    private boolean checkPlayerNameExist(String username) {
        return players.stream().anyMatch(p -> p.getUsername().equals(username));
    }
}
