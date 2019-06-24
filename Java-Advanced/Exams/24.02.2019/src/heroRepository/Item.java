package heroRepository;

public class Item {
    private int strength;
    private int agility;
    private int intelligence;

    public Item(int strength, int agility, int intelligence) {
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Item:").append(System.lineSeparator());
        result.append("  *  Strength: ").append(this.strength).append(System.lineSeparator());
        result.append("  *  Agility: ").append(this.agility).append(System.lineSeparator());
        result.append("  *  Intelligence: ").append(this.intelligence);
        return result.toString().trim();
    }
}
