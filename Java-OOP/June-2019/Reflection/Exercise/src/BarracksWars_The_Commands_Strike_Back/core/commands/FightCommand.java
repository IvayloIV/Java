package BarracksWars_The_Commands_Strike_Back.core.commands;

import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

public class FightCommand extends Command{
    public FightCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
