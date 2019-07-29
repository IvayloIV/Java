package Vehicles_Extension;

public abstract class Vehicle {
    private double fuelQuantity;
    protected double fuelConsumptionPerKm;
    private double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        this.setTankCapacity(tankCapacity);
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
        this.setFuelQuantity(fuelQuantity);
    }

    public void drive(double distance) {
        if (this.fuelConsumptionPerKm * distance > this.fuelQuantity) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " needs refueling");
        }

        this.setFuelQuantity(this.fuelQuantity - this.fuelConsumptionPerKm * distance);
    }

    public void refuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        this.setFuelQuantity(this.fuelQuantity + liters);
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (fuelQuantity > this.getTankCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity = fuelQuantity;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(),
                this.fuelQuantity);
    }
}
