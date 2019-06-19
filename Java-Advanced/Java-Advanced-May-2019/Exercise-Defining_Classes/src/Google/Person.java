package Google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private List<Parent> parents;
    private List<Child> children;
    private List<Pokemon> pokemons;

    public Person(String name) {
        this.name = name;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.pokemons = new ArrayList<>();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    public void addChild(Child child) {
        this.children.add(child);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    @Override
    public String toString() {
        String result = this.name + System.lineSeparator();

        result += "Company:" + System.lineSeparator();
        if (this.company != null) {
            result += this.company + System.lineSeparator();
        }

        result += "Car:" + System.lineSeparator();
        if (this.car != null) {
            result += this.car + System.lineSeparator();
        }

        result += "Pokemon:" + System.lineSeparator();
        for (Pokemon pokemon : this.pokemons) {
            result += pokemon + System.lineSeparator();
        }

        result += "Parents:" + System.lineSeparator();
        for (Parent parent : this.parents) {
            result += parent + System.lineSeparator();
        }

        result += "Children:" + System.lineSeparator();
        for (Child child : this.children) {
            result += child + System.lineSeparator();
        }

        return result;
    }
}
