package Raw_Data;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private List<Tire> tires;

    public Car(String model, Engine engine, Cargo cargo) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = new ArrayList<>();
    }

    public void addTire(Tire tire) {
        this.tires.add(tire);
    }

    public boolean isFragile() {
        return this.cargo.getType().equals("fragile") &&
                this.tires.stream().anyMatch(a -> a.getPressure() < 1);
    }

    public boolean isFlamable() {
        return this.cargo.getType().equals("flamable") &&
                this.engine.getPower() > 250;
    }

    @Override
    public String toString() {
        return this.model;
    }
}
