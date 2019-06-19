package Car_Salesman;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine) {
        this(model, engine, "n/a", "n/a");
    }


    public Car(String model, Engine engine, String color) {
        this(model, engine, "n/a", color);
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine, String.valueOf(weight), "n/a");
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                this.engine +
                "Weight: %s%n" +
                "Color: %s",
                this.model, this.weight, this.color);
    }
}
