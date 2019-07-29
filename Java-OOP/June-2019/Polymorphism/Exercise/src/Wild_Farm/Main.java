package Wild_Farm;

import Wild_Farm.Animals.Animal;
import Wild_Farm.Animals.AnimalBuilder;
import Wild_Farm.Foods.Food;
import Wild_Farm.Foods.FoodBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animals = new ArrayList<>();
        String input;
        while (!(input = bufferedReader.readLine()).equals("End")) {
            String[] animalTokens = input.split("\\s+");
            String[] foodTokens = bufferedReader.readLine().split("\\s+");

            Animal animal = AnimalBuilder.createAnimal(animalTokens);
            Food food = FoodBuilder.createFood(foodTokens[0], Integer.parseInt(foodTokens[1]));

            animal.makeSound();
            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            animals.add(animal);
        }

        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
