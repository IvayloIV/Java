package Vehicles;

public class Car extends Vehicle {
    private static final double AIR_CONDITION = 0.9;

    public Car(double fuelQuantity, double fuelConsumptionPerKm) {
        super(fuelQuantity, fuelConsumptionPerKm + AIR_CONDITION);
    }
}
