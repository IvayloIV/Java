package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTests {
    CustomLinkedList<String> cll;

    @Before
    public void initValues() {
        this.cll = new CustomLinkedList<>();
        cll.add("Pesho");
        cll.add("Gosho");
        cll.add("Tosho");
    }

    @Test
    public void addHeadOfLinkedList() {
        Assert.assertEquals(cll.get(0), "Pesho");
    }

    @Test
    public void addElementInNonEmptyLinkedList() {
        Assert.assertEquals(cll.get(2), "Tosho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getElementWithNegativeIndex() {
        cll.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithOutOfArrayIndex() {
        cll.get(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setElementWithNegativeIndex() {
        cll.set(-1, "Ginka");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWithOutOfArrayIndex() {
        cll.set(3, "Ginka");
    }

    @Test
    public void setFirstElementInIndex() {
        cll.set(0, "Ginka");
        Assert.assertEquals("Ginka", cll.get(0));
    }

    @Test
    public void setLastElementInIndex() {
        cll.set(2, "Ginka");
        Assert.assertEquals("Ginka", cll.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAtElementWithNegativeIndex() {
        cll.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAtWithOutOfArrayIndex() {
        cll.removeAt(3);
    }

    @Test
    public void removeAtFirstIndex() {
        Assert.assertEquals("Pesho", cll.removeAt(0));
    }

    @Test
    public void removeAtLastIndex() {
        Assert.assertEquals("Tosho", cll.removeAt(2));
    }

    @Test
    public void removeWithExistElementAtTheStart() {
        Assert.assertEquals(0, cll.remove("Pesho"));
    }

    @Test
    public void removeWithExistElementAtTheEnd() {
        Assert.assertEquals(2, cll.remove("Tosho"));
    }

    @Test
    public void removeNonExistElement() {
        Assert.assertEquals(-1, cll.remove("Misho"));
    }

    @Test
    public void indexOfExistElementAtTheStart() {
        Assert.assertEquals(0, cll.indexOf("Pesho"));
    }

    @Test
    public void indexOfExistElementAtTheEnd() {
        Assert.assertEquals(2, cll.indexOf("Tosho"));
    }

    @Test
    public void indexOfNonExistElement() {
        Assert.assertEquals(-1, cll.indexOf("Misho"));
    }

    @Test
    public void containsWithExistElement() {
        Assert.assertTrue(cll.contains("Pesho"));
    }

    @Test
    public void containsWithNonExistElement() {
        Assert.assertFalse(cll.contains("Misho"));
    }
}
