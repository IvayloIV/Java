package test.christmas;

import christmas.Present;
import christmas.PresentBag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ChristmasTests {
    private Present present;
    private PresentBag presentBag;

    @Before
    public void createInstances() {
        this.presentBag = new PresentBag();
        this.present = new Present("Name", 1.5);
    }

    @Test
    public void shouldCheckEmptyData() {
        int expected = 0;
        int actual = this.presentBag.getCount();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPresentsWithSameNames() {
        this.presentBag.create(this.present);
        this.presentBag.create(this.present);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPresentsWithSameNamesDiffObjects() {
        this.presentBag.create(this.present);
        Present present = new Present("Name", 2);
        this.presentBag.create(present);
    }

    @Test
    public void createPresentsWithDiffNames() {
        this.presentBag.create(this.present);
        Present present2 = new Present("Name2", 1.5);
        Present present3 = new Present("Name3", 1.143);
        String result2 = this.presentBag.create(present2);
        String result3 = this.presentBag.create(present3);
        Assert.assertEquals("Successfully added present Name2 with magic 1.50", result2);
        Assert.assertEquals("Successfully added present Name3 with magic 1.14", result3);
        Assert.assertEquals(3, this.presentBag.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void removePresentWithNull() {
        this.presentBag.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void removePresentWithEmptyString() {
        this.presentBag.remove("\t              \n");
    }

    @Test
    public void removeWithNotExistPresentName() {
        this.presentBag.create(this.present);
        Assert.assertFalse(this.presentBag.remove("HappyName"));
    }

    @Test
    public void removeWithExistPresentName() {
        this.presentBag.create(this.present);
        Present present3 = new Present("Name4", 1.143);
        this.presentBag.create(present3);
        Assert.assertTrue(this.presentBag.remove("Name"));
        Assert.assertEquals(1, this.presentBag.getCount());
    }

    @Test
    public void getPresentWithLeastMagicEmpty() {
        Assert.assertNull(this.presentBag.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentWithLeastMagicWithCorrectData() {
        Present present3 = new Present("Name3", 1.143);
        this.presentBag.create(this.present);
        this.presentBag.create(present3);
        Present present = this.presentBag.getPresentWithLeastMagic();
        Assert.assertEquals(present3.getName(), present.getName());
    }

    @Test
    public void getPresentWithLeastMagicWithInvalidData() {
        Present present3 = new Present("Name3", 4);
        this.presentBag.create(this.present);
        this.presentBag.create(present3);
        Present present = this.presentBag.getPresentWithLeastMagic();
        Assert.assertEquals(this.present.getName(), present.getName());
    }

    @Test
    public void getPresentWithEmptyData() {
        Assert.assertNull(this.presentBag.getPresent("Bla"));
    }

    @Test
    public void getPresentWithNotExistName() {
        this.presentBag.create(this.present);
        Assert.assertNull(this.presentBag.getPresent("Bla"));
    }

    @Test
    public void getPresentWithTwoPresents() {
        Present present3 = new Present("Name3", 4);
        this.presentBag.create(this.present);
        this.presentBag.create(present3);
        Present actual = this.presentBag.getPresent("Name3");
        Assert.assertEquals(present3.getName(), actual.getName());
    }

    @Test
    public void getPresentsWithNotModData() {
        Present present3 = new Present("Name3", 4);
        this.presentBag.create(this.present);
        this.presentBag.create(present3);
        Assert.assertEquals(2, this.presentBag.getPresents().size());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void getPresentsAndModDate() {
        Present present3 = new Present("Name3", 4);
        this.presentBag.create(this.present);
        this.presentBag.create(present3);
        Collection<Present> presents = this.presentBag.getPresents();
        presents.add(new Present("Name4", 5));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfNullPresent() {
        this.presentBag.create(null);
    }
}
