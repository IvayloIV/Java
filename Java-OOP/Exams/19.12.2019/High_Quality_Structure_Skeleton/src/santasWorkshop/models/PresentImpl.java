package santasWorkshop.models;

import static santasWorkshop.common.ExceptionMessages.*;

public class PresentImpl implements Present {

    private String name;

    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getEnergyRequired() {
        return energyRequired;
    }

    private void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.energyRequired == 0;
    }

    @Override
    public void getCrafted() {
        this.setEnergyRequired(this.energyRequired - 10);
//        this.energyRequired -= 10;
//        if (this.energyRequired < 0) {
//            this.energyRequired = 0;
//        }
    }
}
