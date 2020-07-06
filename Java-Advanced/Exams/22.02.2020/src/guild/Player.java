package guild;

public class Player {

    private String name;

    private String clazz;

    private String rank = "Trial";

    private String description = "n/a";

    public Player(String name, String clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public String getClazz() {
        return clazz;
    }

    public String getRank() {
        return rank;
    }

    public String getDescription() {
        return description;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player ").append(this.name).append(": ").append(this.clazz);
        sb.append(System.lineSeparator());
        sb.append("Rank: ").append(this.rank).append(System.lineSeparator());
        sb.append("Description: ").append(this.description);
        return sb.toString();
    }
}
