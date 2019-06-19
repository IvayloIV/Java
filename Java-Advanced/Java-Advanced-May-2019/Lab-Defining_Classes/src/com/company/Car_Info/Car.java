package com.company.Car_Info;

public class Car {
    public String make;
    public String model;
    public int housePower;

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
