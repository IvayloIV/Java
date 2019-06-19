package Car_Salesman;

public class Engine {
    private String model;
    private double power;
    private String displacement;
    private String efficiency;

    public Engine(String model, double power, String displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public Engine(String model, double power) {
        this(model, power, "n/a", "n/a");
    }

    public Engine(String model, double power, String efficiency) {
        this(model, power, "n/a", efficiency);
    }

    public Engine(String model, double power, int displacement) {
        this(model, power, String.valueOf(displacement), "n/a");
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                "Power: %.0f%n" +
                "Displacement: %s%n" +
                "Efficiency: %s%n",
                this.model, this.power, this.displacement, this.efficiency);
    }
}
