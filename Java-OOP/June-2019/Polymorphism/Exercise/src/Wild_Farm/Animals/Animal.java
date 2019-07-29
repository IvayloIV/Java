package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

import java.text.DecimalFormat;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private int eatenFood;

    protected Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
    }

    public int getEatenFood() {
        return eatenFood;
    }

    protected void setEatenFood(int eatenFood) {
        this.eatenFood = eatenFood;
    }

    public String getAnimalType() {
        return animalType;
    }

    public abstract void makeSound();
    public abstract void eat(Food food);

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.##");
        return String.format("%s[%s, %s]",
                this.animalType,
                this.animalName,
                format.format(this.animalWeight));
    }
}
