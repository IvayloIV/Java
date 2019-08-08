package BarracksWars_The_Commands_Strike_Back;

import BarracksWars_The_Commands_Strike_Back.core.Engine;
import BarracksWars_The_Commands_Strike_Back.core.factories.UnitFactoryImpl;
import BarracksWars_The_Commands_Strike_Back.data.UnitRepository;
import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.Runnable;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
