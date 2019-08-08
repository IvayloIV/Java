package BarracksWars_The_Commands_Strike_Back.core.factories;

import BarracksWars_The_Commands_Strike_Back.interfaces.Unit;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"BarracksWars_The_Commands_Strike_Back.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		Class utilClass = null;
		try {
			utilClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor declaredConstructor = null;
		try {
			declaredConstructor = utilClass.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		declaredConstructor.setAccessible(true);
		Object instanceUtil = null;
		try {
			instanceUtil = declaredConstructor.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return (Unit)instanceUtil;
	}
}
