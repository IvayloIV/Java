package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTests {
    @Test
    public void setElementsWithNormalCountOfPeople() throws OperationNotSupportedException {
        Database db = new Database(new Person[16]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void setElementsWithMoreThanMaxCountOfPeople() throws OperationNotSupportedException {
        Database db = new Database(new Person[17]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void setElementsWithZeroCountOfPeople() throws OperationNotSupportedException {
        Database db = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addNullPerson() throws OperationNotSupportedException {
        Database db = new Database(new Person(2, "Pesho"));
        db.add(null);
    }

    @Test
    public void addPeopleToDb() throws OperationNotSupportedException {
        Database db = new Database(new Person(2, "Pesho"));
        db.add(new Person(3, "Gosho"));
    }

    @Test
    public void removeLastPersonFromDb() throws OperationNotSupportedException {
        Database db = new Database(new Person(2, "Pesho"), new Person(5, "Pesho2"));
        db.add(new Person(3, "Gosho"));
        db.remove();
        Assert.assertEquals(db.getElements().length, 2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeFromEmptyDb() throws OperationNotSupportedException {
        Database db = new Database(new Person(2, "Pesho"));
        db.remove();
        db.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByNullUsername() throws OperationNotSupportedException {
        Database db = new Database(new Person(2, "Pesho"));
        db.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findPeopleWithTheSameUsername() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(2, "Pesho"));
        db.findByUsername("Pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findPersonByUsernameCaseSensitive() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(2, "Gosho"));
        db.add(new Person(3, "Peter"));
        db.findByUsername("gosho");
    }

    @Test
    public void findPersonByUsername() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(2, "Gosho"));
        db.add(new Person(3, "Peter"));
        Assert.assertEquals(db.findByUsername("Gosho").getId(), 2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findPeopleWithTheSameIds() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(1, "Gosho"));
        db.add(new Person(2, "Peter"));
        db.findById(1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findPeopleWithNonExistId() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(2, "Gosho"));
        db.add(new Person(3, "Peter"));
        db.findById(4);
    }

    @Test
    public void findPersonById() throws OperationNotSupportedException {
        Database db = new Database(new Person(1, "Pesho"));
        db.add(new Person(2, "Gosho"));
        db.add(new Person(3, "Peter"));
        Assert.assertEquals(db.findById(3).getUsername(), "Peter");
    }
}
