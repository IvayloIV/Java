import java.util.ArrayList;
import java.util.List;

public class Dummy implements Target {

    private int health;
    private int experience;
    private RandomProvider randomProvider;
    private List<Weapon> items;

    public Dummy(int health, int experience) {
        this.health = health;
        this.experience = experience;
        this.randomProvider = new RandomProvider();
        this.items = new ArrayList<>();
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Weapon giveWeapon() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        int index = this.randomProvider.nextInt(this.items.size());
        return this.items.get(index);
    }
}
