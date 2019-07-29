package Vehicles_Extension;

public class Car extends Vehicle {
    private static final double AIR_CONDITION = 0.9;

    public Car(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKm + AIR_CONDITION, tankCapacity);
    }
}
