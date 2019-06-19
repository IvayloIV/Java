package Pokemon_Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Trainer implements Comparable<Trainer> {
    private String name;
    private int numberOfBadges;
    private List<Pokemon> pokemons;

    public Trainer(String name) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public void checkPokemons(String element) {
        if (pokemons.stream().anyMatch(a -> a.getElement().equals(element))) {
            this.increaseBadges();
        } else {
            reducePokemonsHealth();
        }
    }

    private void increaseBadges() {
        this.numberOfBadges++;
    }

    private void reducePokemonsHealth() {
        pokemons.forEach(p -> p.reduceHealth(10));
        this.pokemons = this.pokemons.stream()
                .filter(a -> a.getHealth() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.numberOfBadges, this.pokemons.size());
    }

    @Override
    public int compareTo(Trainer t) {
        return t.numberOfBadges - this.numberOfBadges;
    }
}
