import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroMockingTests {
    private static final String HERO_NAME = "Pesho";
    private static final int TARGET_XP = 10;

    @Test
    public void attackGainsExperienceIfTargetIsDead() {
        Weapon mockedWeapon = Mockito.mock(Weapon.class);
        Mockito.when(mockedWeapon.getAttackPoints()).thenReturn(10);
        Mockito.when(mockedWeapon.getDurabilityPoints()).thenReturn(0);

        Target targetWeapon = Mockito.mock(Target.class);
        Mockito.when(targetWeapon.giveExperience()).thenReturn(TARGET_XP);
        Mockito.when(targetWeapon.isDead()).thenReturn(true);

        Hero hero = new Hero(HERO_NAME, mockedWeapon);
        hero.attack(targetWeapon);
        Assert.assertEquals("Wrong experience", hero.getExperience(), TARGET_XP);
    }
}
