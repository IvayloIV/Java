package Barracks_Wars.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface UnitFactory {

    Unit createUnit(String unitType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException;
}