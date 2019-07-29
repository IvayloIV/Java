package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

public class Zebra extends Mammal {
    protected Zebra(String animalName, String animalType, Double animalWeight, String livingRoom) {
        super(animalName, animalType, animalWeight, livingRoom);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        super.eat(food, "Vegetable");
    }
}
