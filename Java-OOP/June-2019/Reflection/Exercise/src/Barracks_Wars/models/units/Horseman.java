package Barracks_Wars.models.units;

public class Horseman extends AbstractUnit {
    private static final int HORSEMAN_HEALTH = 30;
    private static final int HORSEMAN_DAMAGE = 15;

    protected Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_DAMAGE);
    }
}
