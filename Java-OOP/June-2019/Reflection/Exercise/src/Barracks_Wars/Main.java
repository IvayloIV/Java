package Barracks_Wars;

import Barracks_Wars.interfaces.Repository;
import Barracks_Wars.interfaces.Runnable;
import Barracks_Wars.interfaces.UnitFactory;
import Barracks_Wars.core.Engine;
import Barracks_Wars.core.factories.UnitFactoryImpl;
import Barracks_Wars.data.UnitRepository;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
