package BarracksWars_The_Commands_Strike_Back.core.commands;

import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.Unit;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

public class AddCommand extends Command {
    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        Unit unitToAdd = super.getUnitFactory().createUnit(unitType);
        super.getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
