public interface Weapon {
    void attack(Target target);

    int getAttackPoints();

    int getDurabilityPoints();

    Iterable<Weapon> getInventory();
}
