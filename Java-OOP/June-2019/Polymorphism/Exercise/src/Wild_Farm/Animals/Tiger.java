package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

public class Tiger extends Mammal {
    protected Tiger(String animalName, String animalType, Double animalWeight, String livingRoom) {
        super(animalName, animalType, animalWeight, livingRoom);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        super.eat(food, "Meat");
    }
}
