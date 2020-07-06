package guild;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Guild {

    private List<Player> roster;

    private String name;

    private int capacity;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.count() < this.capacity) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        return this.roster.removeIf(p -> p.getName().equals(name));
    }

    public void promotePlayer(String name) {
        Optional<Player> player = this.getPlayerByName(name);
        player.ifPresent(p -> p.setRank("Member"));
    }

    public void demotePlayer(String name) {
        Optional<Player> player = this.getPlayerByName(name);
        player.ifPresent(p -> p.setRank("Trial"));
    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> players = new ArrayList<>();
        this.roster.stream()
                .filter(p -> p.getClazz().equals(clazz))
                .forEach(players::add);

        this.roster.removeAll(players);
        return players.toArray(new Player[0]);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append("Players in the guild: ").append(this.name).append(":");
        sb.append(System.lineSeparator());
        this.roster.forEach(p -> sb.append(p).append(System.lineSeparator()));
        return sb.toString().trim();
    }


    public int count() {
        return this.roster.size();
    }

    private Optional<Player> getPlayerByName(String name) {
        return this.roster.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }
}
