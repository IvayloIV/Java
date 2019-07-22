package Pizza_Calories;

public class Topping {
    private final static double CALORIES_PER_GRAM = 2;
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public String getToppingType() {
        return toppingType;
    }

    private void setToppingType(String toppingType) {
        this.getToppingValue(toppingType);
        this.toppingType = toppingType;
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(this.toppingType + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (CALORIES_PER_GRAM * this.getWeight()) *
                this.getToppingValue(this.getToppingType());
    }

    private double getToppingValue(String toppingType) {
        switch (toppingType.toLowerCase()) {
            case "meat":
                return 1.2;
            case "veggies":
                return 0.8;
            case "cheese":
                return 1.1;
            case "sauce":
                return 0.9;
            default:
                throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }
}
