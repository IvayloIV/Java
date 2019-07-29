package Vehicles;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionPerKm;

    public Vehicle(double fuelQuantity, double fuelConsumptionPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
    }

    public void drive(double distance) {
        if (this.fuelConsumptionPerKm * distance > this.fuelQuantity) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " needs refueling");
        }

        this.fuelQuantity -= this.fuelConsumptionPerKm * distance;
    }

    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(),
                this.fuelQuantity);
    }
}
