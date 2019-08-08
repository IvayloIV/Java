package Barracks_Wars.core.factories;

import Barracks_Wars.interfaces.Unit;
import Barracks_Wars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"Barracks_Wars.models.units.";

	@Override
	public Unit createUnit(String unitType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		Class utilClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
		Constructor declaredConstructor = utilClass.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		Object instanceUtil = declaredConstructor.newInstance();
		return (Unit)instanceUtil;
	}
}
