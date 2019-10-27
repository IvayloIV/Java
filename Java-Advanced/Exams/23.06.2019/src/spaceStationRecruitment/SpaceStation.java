package spaceStationRecruitment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpaceStation {
    private String name;

    private int capacity;

    private List<Astronaut> data;

    public SpaceStation(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public void add(Astronaut astronaut) {
        if (this.getCount() >= this.capacity) {
            return;
        }

        this.data.add(astronaut);
    }

    public boolean remove(String name) {
        boolean isExist = this.data.stream().anyMatch(a -> a.getName().equals(name));
        this.data = this.data.stream()
                .filter(a -> !a.getName().equals(name))
                .collect(Collectors.toList());
        return isExist;
    }

    public Astronaut getOldestAstronaut() {
        List<Astronaut> astronautsCopy = new ArrayList<>(this.data);
        astronautsCopy.sort((a, b) -> b.getAge() - a.getAge());
        return astronautsCopy.stream().findFirst().orElse(null);
    }

    public Astronaut getAstronaut(String name) {
        return this.data.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Astronauts working at Space Station %s:%n", this.name));
        for (Astronaut astronaut : this.data) {
            sb.append(astronaut)
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
