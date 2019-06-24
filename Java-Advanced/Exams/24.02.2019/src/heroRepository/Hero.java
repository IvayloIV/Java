package heroRepository;

public class Hero {
    private String name;
    private int level;
    private Item item;

    public Hero(String name, int level, Item item) {
        this.name = name;
        this.level = level;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Hero: %s – %d", this.name, this.level)).append(System.lineSeparator());
        result.append("  *  Strength: ").append(this.item.getStrength()).append(System.lineSeparator());
        result.append("  *  Agility: ").append(this.item.getAgility()).append(System.lineSeparator());
        result.append("  *  Intelligence: ").append(this.item.getIntelligence());
        return result.toString().trim();
    }
}
