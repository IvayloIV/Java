package Wild_Farm.Animals;

import Wild_Farm.Foods.Food;

public abstract class Mammal extends Animal {
    private String livingRoom;

    protected Mammal(String animalName, String animalType, Double animalWeight, String livingRoom) {
        super(animalName, animalType, animalWeight);
        this.livingRoom = livingRoom;
    }

    public void eat(Food food, String... eatenFoods) {
        String foodName = food.getClass().getSimpleName();

        for (String eatenFood : eatenFoods) {
            if (eatenFood.equals(foodName)) {
                this.setEatenFood(this.getEatenFood() + food.getQuantity());
                return;
            }
        }
        String type = this.getAnimalType();
        if (type.equals("Mouse")) {
            type = "Mice";
        } else {
            type += "s";
        }
        throw new IllegalArgumentException(type + " are not eating that type of food!");
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return String.format("%s, %s, %d]",
                parent.substring(0, parent.length() - 1),
                this.livingRoom,
                super.getEatenFood());
    }
}
