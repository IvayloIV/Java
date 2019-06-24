package heroRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HeroRepository {
    private List<Hero> data;

    public HeroRepository() {
        this.data = new ArrayList<>();
    }

    public void add(Hero entity) {
        this.data.add(entity);
    }

    public void remove(String name) {
        this.data = this.data.stream()
                .filter(a -> !a.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Hero getHeroWithHighestStrength() {
        return this.data.stream()
                .max(Comparator.comparingInt(a -> a.getItem().getStrength()))
                .get();
    }

    public Hero getHeroWithHighestAgility() {
        return this.data.stream()
                .max(Comparator.comparingInt(a -> a.getItem().getAgility()))
                .get();
    }

    public Hero getHeroWithHighestIntelligence() {
        return this.data.stream()
                .max(Comparator.comparingInt(a -> a.getItem().getIntelligence()))
                .get();
    }

    public int getCount() {
        return this.data.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Hero hero : this.data) {
            result.append(hero).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
