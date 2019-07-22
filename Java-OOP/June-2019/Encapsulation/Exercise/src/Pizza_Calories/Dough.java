package Pizza_Calories;

public class Dough {
    private static final int CALORIES_PER_GRAM = 2;
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public String getFlourType() {
        return flourType;
    }

    private void setFlourType(String flourType) {
        this.getFlourTypeValue(flourType);
        this.flourType = flourType;
    }

    public String getBakingTechnique() {
        return bakingTechnique;
    }

    private void setBakingTechnique(String bakingTechnique) {
        this.getBakingTechValue(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (CALORIES_PER_GRAM * this.getWeight()) *
                this.getFlourTypeValue(this.getFlourType()) *
                this.getBakingTechValue(this.getBakingTechnique());
    }

    private double getFlourTypeValue(String flourType) {
        switch (flourType.toLowerCase()) {
            case "white":
                return 1.5;
            case "wholegrain":
                return 1.0;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private double getBakingTechValue(String bakingTechnique) {
        switch (bakingTechnique.toLowerCase()) {
            case "crispy":
                return 0.9;
            case "chewy":
                return 1.1;
            case "homemade":
                return 1.0;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }
}
