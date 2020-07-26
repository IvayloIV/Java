package santasWorkshop.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static santasWorkshop.common.ExceptionMessages.*;

public abstract class BaseDwarf implements Dwarf {

    private String name;

    private int energy;

    private List<Instrument> instruments;

    public BaseDwarf(String name, int energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new NullPointerException(DWARF_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    private void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DWARF_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return Collections.unmodifiableCollection(this.instruments);
    }

    @Override
    public void work() {
        this.reduceEnergy(10);
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy > 0;
    }

    protected void reduceEnergy(int energy) {
        this.energy -= energy;
    }

    @Override
    public String toString() {
        long notBrokenInst = this.getInstruments().stream().filter(i -> !i.isBroken()).count();
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s", this.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Energy: %d", this.energy));
        sb.append(System.lineSeparator());
        sb.append(String.format("Instruments %d not broken left", notBrokenInst));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
