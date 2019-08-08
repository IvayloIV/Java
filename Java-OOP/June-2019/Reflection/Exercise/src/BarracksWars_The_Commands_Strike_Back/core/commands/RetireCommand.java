package BarracksWars_The_Commands_Strike_Back.core.commands;

import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

public class RetireCommand extends Command {
    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String value = super.getData()[1];
        super.getRepository().removeUnit(value);
        return value + " retired!";
    }
}
