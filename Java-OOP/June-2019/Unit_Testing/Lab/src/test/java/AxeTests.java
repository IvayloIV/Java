import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTests {
    private static final int DEFAULT_AXE_ATTACK = 20;
    private static final int DEFAULT_AXE_DURABILITY = 2;
    private static final int DEFAULT_DUMMY_HEALTH = 40;
    private static final int DEFAULT_DUMMY_EXPERIENCE = 20;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initValues() {
        this.axe = new Axe(DEFAULT_AXE_ATTACK, DEFAULT_AXE_DURABILITY);
        this.dummy = new Dummy(DEFAULT_DUMMY_HEALTH, DEFAULT_DUMMY_EXPERIENCE);
    }

    @Test
    public void lossDurabilityAfterEachAttack() {
        axe.attack(dummy);
        Assert.assertEquals("Loses durability.", axe.getDurabilityPoints(), 1);
    }

    @Test(expected = IllegalStateException.class)
    public void attackingWithBrokenWeapon() {
        axe.attack(dummy);
        axe.attack(dummy);
        axe.attack(dummy);
    }
}
