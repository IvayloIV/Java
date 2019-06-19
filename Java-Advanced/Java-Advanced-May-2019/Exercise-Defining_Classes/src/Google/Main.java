package Google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> people = new HashMap<>();
        String line;
        while (!"End".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");

            String name = tokens[0];
            String command = tokens[1];
            people.putIfAbsent(name, new Person(name));

            switch (command) {
                case "company":
                    addCompany(people, tokens, name);
                    break;
                case "car":
                    addCar(people, tokens, name);
                    break;
                case "pokemon":
                    addPokemon(people, tokens, name);
                    break;
                case "parents":
                    addParent(people, tokens, name);
                    break;
                case "children":
                    addChild(people, tokens, name);
                    break;
            }
        }

        String personName = bufferedReader.readLine();
        System.out.println(people.get(personName));
    }

    private static void addChild(Map<String, Person> people, String[] tokens, String name) {
        String childName = tokens[2];
        String childBirthday = tokens[3];
        Child child = new Child(childName, childBirthday);
        people.get(name).addChild(child);
    }

    private static void addParent(Map<String, Person> people, String[] tokens, String name) {
        String parentName = tokens[2];
        String parentBirthday = tokens[3];
        Parent parent = new Parent(parentName, parentBirthday);
        people.get(name).addParent(parent);
    }

    private static void addPokemon(Map<String, Person> people, String[] tokens, String name) {
        String pokemonName = tokens[2];
        String pokemonType = tokens[3];
        Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
        people.get(name).addPokemon(pokemon);
    }

    private static void addCar(Map<String, Person> people, String[] tokens, String name) {
        String carModel = tokens[2];
        int carSpeed = Integer.parseInt(tokens[3]);
        Car car = new Car(carModel, carSpeed);
        people.get(name).setCar(car);
    }

    private static void addCompany(Map<String, Person> people, String[] tokens, String name) {
        String companyName = tokens[2];
        String department = tokens[3];
        double salary = Double.parseDouble(tokens[4]);
        Company company = new Company(companyName, department, salary);
        people.get(name).setCompany(company);
    }
}
