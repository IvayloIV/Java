package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

public class Cat extends Felime {
    private String breed;
    protected Cat(String animalName, String animalType, Double animalWeight, String livingRoom, String breed) {
        super(animalName, animalType, animalWeight, livingRoom);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.eat(food, "Meat", "Vegetable");
    }

    @Override
    public String toString() {
        String parent = super.toString();
        int indexOfComma = parent.indexOf(", ");
        String leftPart = parent.substring(0, indexOfComma);
        String rightPart = parent.substring(indexOfComma);
        return String.format("%s, %s%s",
                leftPart,
                this.breed,
                rightPart);
    }
}
