import org.junit.Assert;
import org.junit.Test;

public class HeroTests {
    private static final String HERO_NAME = "Pesho";
    private static final int TARGET_XP = 10;

    @Test
    public void attackGainsExperienceIfTargetIsDead() {
        Weapon weapon = new Weapon() {
            @Override
            public void attack(Target target) {}

            @Override
            public int getAttackPoints() {
                return 10;
            }

            @Override
            public int getDurabilityPoints() {
                return 0;
            }

            @Override
            public Iterable<Weapon> getInventory() {
                return null;
            }
        };

        Target target = new Target() {
            @Override
            public int getHealth() {
                return 0;
            }

            @Override
            public void takeAttack(int attackPoints) {}

            @Override
            public int giveExperience() {
                return TARGET_XP;
            }

            @Override
            public boolean isDead() {
                return true;
            }

            @Override
            public Weapon giveWeapon() {
                return null;
            }
        };

        Hero hero = new Hero(HERO_NAME, weapon);
        hero.attack(target);
        Assert.assertEquals("Wrong experience", hero.getExperience(), TARGET_XP);
    }
}
