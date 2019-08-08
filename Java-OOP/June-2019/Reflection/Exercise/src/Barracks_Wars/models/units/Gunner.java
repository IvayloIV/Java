package Barracks_Wars.models.units;

public class Gunner extends AbstractUnit {
    private static final int GUNNER_HEALTH = 30;
    private static final int GUNNER_DAMAGE = 15;

    protected Gunner() {
        super(GUNNER_HEALTH, GUNNER_DAMAGE);
    }
}
