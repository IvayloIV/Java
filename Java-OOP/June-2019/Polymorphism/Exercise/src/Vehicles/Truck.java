package Vehicles;

public class Truck extends Vehicle {
    private static final double AIR_CONDITION = 1.6;
    private static final double TINY_HOLE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumptionPerKm) {
        super(fuelQuantity, fuelConsumptionPerKm + AIR_CONDITION);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * TINY_HOLE);
    }
}
