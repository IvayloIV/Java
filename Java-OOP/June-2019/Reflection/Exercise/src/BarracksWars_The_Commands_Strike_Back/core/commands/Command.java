package BarracksWars_The_Commands_Strike_Back.core.commands;

import BarracksWars_The_Commands_Strike_Back.interfaces.Executable;
import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;


public abstract class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    protected Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected String[] getData() {
        return data;
    }

    protected Repository getRepository() {
        return repository;
    }

    protected UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
