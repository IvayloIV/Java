package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

public class Mouse extends Mammal {
    protected Mouse(String animalName, String animalType, Double animalWeight, String livingRoom) {
        super(animalName, animalType, animalWeight, livingRoom);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        super.eat(food, "Vegetable");
    }
}
