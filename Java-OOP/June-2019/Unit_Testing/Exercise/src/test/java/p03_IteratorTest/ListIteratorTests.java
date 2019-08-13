package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTests {
    private ListIterator li;

    @Before
    public void initValues() throws OperationNotSupportedException {
        li = new ListIterator("Pesho", "Gosho", "Tosho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void initializeWithNull() throws OperationNotSupportedException {
        String[] arr = null;
        new ListIterator(arr);
    }

    @Test
    public void moveToTheExistIndex() {
        li.move();
        Assert.assertTrue(li.move());
    }

    @Test
    public void moveToNonExistIndex() {
        li.move();
        li.move();
        Assert.assertFalse(li.move());
    }

    @Test
    public void hasNextInTheCollection() {
        li.move();
        Assert.assertTrue(li.move());
    }

    @Test
    public void hasNextOutOfCollection() {
        li.move();
        li.move();
        Assert.assertFalse(li.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void printWithEmptyCollection() throws OperationNotSupportedException {
        ListIterator li = new ListIterator();
        li.print();
    }

    @Test
    public void printStartElement() {
        Assert.assertEquals(li.print(), "Pesho");
    }

    @Test
    public void printEndElement() {
        li.move();
        li.move();
        Assert.assertEquals(li.print(), "Tosho");
    }
}
