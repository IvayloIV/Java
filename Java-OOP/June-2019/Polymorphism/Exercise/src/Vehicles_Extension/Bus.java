package Vehicles_Extension;

public class Bus extends Vehicle {
    public static final double AIR_CONDITION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKm + AIR_CONDITION, tankCapacity);
    }

    public void driveEmpty(double distance) {
        super.fuelConsumptionPerKm -= 1.4;
        try {
            super.drive(distance);
        } finally {
            super.fuelConsumptionPerKm += 1.4;
        }
    }
}
