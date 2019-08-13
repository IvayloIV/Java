package p01_Database;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTests {
    @Test
    public void setElementsWithNormalCapacity() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3, 4);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void setElementsWithZeroCapacity() throws OperationNotSupportedException {
        Database db = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void setElementsWithMoreThanMaxCapacity() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3, 4, 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addElementToTheFullDb() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3, 4);
        db.add(12);
    }

    @Test
    public void addElementToTheEnd() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3);
        db.add(66);
        Integer[] elements = db.getElements();
        Integer[] expected = new Integer[] {2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3, 66};
        Assert.assertArrayEquals(elements, expected);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addNullElement() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3);
        db.add(null);
    }

    @Test
    public void removeLastElement() throws OperationNotSupportedException {
        Database db = new Database(2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2, 3);
        db.remove();
        Integer[] expected = new Integer[] {2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8 ,5, 3, 2};
        Assert.assertArrayEquals(db.getElements(), expected);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeFromEmptyArray() throws OperationNotSupportedException {
        Database db = new Database(1);
        db.remove();
        db.remove();
    }

    @Test
    public void getElementsWithHalfEmptyElements() throws OperationNotSupportedException {
        Database db = new Database(1, 2, 3, 4, 5);
        Integer[] expected = new Integer[] {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(db.getElements(), expected);
    }

}