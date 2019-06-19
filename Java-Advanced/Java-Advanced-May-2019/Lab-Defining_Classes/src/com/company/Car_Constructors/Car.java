package com.company.Car_Constructors;

public class Car {
    public String make;
    public String model;
    public int housePower;

    public Car(String make) {
        this(make, "unknown", -1);
    }

    public Car(String make, String model, int housePower) {
        this.make = make;
        this.model = model;
        this.housePower = housePower;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHousePower() {
        return this.housePower;
    }

    public void setHousePower(int housePower) {
        this.housePower = housePower;
    }

    @Override
    public String toString() {
        return String.format("The car is: %s %s - %d HP.", this.make, this.model, this.housePower);
    }
}
