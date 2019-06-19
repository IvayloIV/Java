package Pokemon_Trainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Trainer> trainers = new LinkedHashMap<>();
        String line;
        while (!"Tournament".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");

            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            Trainer trainer = new Trainer(trainerName);
            trainers.putIfAbsent(trainerName, trainer);
            trainers.get(trainerName).addPokemon(pokemon);
        }

        while (!"End".equals(line = bufferedReader.readLine())) {
            for (Map.Entry<String, Trainer> kvp : trainers.entrySet()) {
                kvp.getValue().checkPokemons(line);
            }
        }

        trainers.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted()
                .forEach(System.out::println);
    }
}
