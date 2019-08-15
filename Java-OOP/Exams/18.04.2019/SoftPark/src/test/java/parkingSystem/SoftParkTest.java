package parkingSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SoftParkTest {
    private SoftPark softPark;
    private Map<String, Car> parking;

    @Before
    public void initValues() {
        this.softPark = new SoftPark();
        this.parking = new HashMap<>();

        this.parking.put("A1", null);
        this.parking.put("A2", null);
        this.parking.put("A3", null);
        this.parking.put("A4", null);
        this.parking.put("B1", null);
        this.parking.put("B2", null);
        this.parking.put("B3", null);
        this.parking.put("B4", null);
        this.parking.put("C1", null);
        this.parking.put("C2", null);
        this.parking.put("C3", null);
        this.parking.put("C4", null);
    }

    @Test
    public void getParkingWithCorrectCount() {
        Assert.assertEquals(12, this.softPark.getParking().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getParkingWithUnmodifiedCollection() {
        this.softPark.getParking().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkParkingSpotNotExist() {
        this.softPark.parkCar("F1", new Car("Japan", "8888"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkParkingSpotThatAlreadyTaken() {
        this.softPark.parkCar("A1", new Car("Japan", "8888"));
        this.softPark.parkCar("A1", new Car("China", "1111"));
    }

    @Test
    public void checkParkingSpotWithValidPlace() {
        String result = this.softPark.parkCar("A1", new Car("Japan", "8888"));
        Assert.assertEquals("Car:8888 parked successfully!", result);
    }

    @Test(expected = IllegalStateException.class)
    public void parkSameCarTwice() {
        Car car = new Car("Japan", "8888");
        this.softPark.parkCar("A1", car);
        this.softPark.parkCar("B1", car);
    }

    @Test(expected = IllegalStateException.class)
    public void parkTwoCars() {
        Car car1 = new Car("Japan", "8888");
        Car car2 = new Car("Chine", "1111");
        this.softPark.parkCar("A1", car1);
        String result = this.softPark.parkCar("B1", car2);

        Assert.assertEquals("Car:1111 parked successfully!", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCarNotExistParkSpot() {
        Car car = new Car("Japan", "8888");
        this.softPark.parkCar("A1", car);
        this.softPark.removeCar("F1", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCarWithOtherParkCar() {
        Car car1 = new Car("Japan", "8888");
        Car car2 = new Car("Chine", "1111");
        this.softPark.parkCar("A1", car1);
        this.softPark.removeCar("A1", car2);
    }

    @Test
    public void removeCarSuccessCheckMessage() {
        Car car1 = new Car("Japan", "8888");
        this.softPark.parkCar("A1", car1);
        String result = this.softPark.removeCar("A1", car1);

        Assert.assertEquals("Remove car:8888 successfully!", result);
    }

    @Test
    public void removeCarSuccessCheckNullPlace() {
        Car car1 = new Car("Japan", "8888");
        this.softPark.parkCar("A1", car1);
        this.softPark.removeCar("A1", car1);
        String result = this.softPark.parkCar("A1", car1);

        Assert.assertEquals("Car:8888 parked successfully!", result);
    }
}