import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTests {
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_EXPERIENCE = 11;

    private Dummy dummy;

    @Before
    public void initValues() {
        this.dummy = new Dummy(DEFAULT_HEALTH, DEFAULT_EXPERIENCE);
    }

    @Test
    public void dummyLosesHealthIfAttacked() {
        dummy.takeAttack(5);
        Assert.assertEquals("Loses health.", 5, dummy.getHealth());
    }

    @Test
    public void dummyAttackedWithPositiveHealth() {
        dummy.takeAttack(9);
        dummy.takeAttack(1);
    }

    @Test(expected = IllegalStateException.class)
    public void dummyAttackedWithZeroHealth() {
        dummy.takeAttack(10);
        dummy.takeAttack(1);
    }

    @Test(expected = IllegalStateException.class)
    public void dummyAttackedWithNegativeHealth() {
        dummy.takeAttack(11);
        dummy.takeAttack(1);
    }

    @Test
    public void dummyGaveExperience() {
        dummy.takeAttack(10);
        Assert.assertEquals("Gave experience.", 11, dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void dummyGaveExperienceLive() {
        dummy.takeAttack(9);
        dummy.giveExperience();
    }
}
