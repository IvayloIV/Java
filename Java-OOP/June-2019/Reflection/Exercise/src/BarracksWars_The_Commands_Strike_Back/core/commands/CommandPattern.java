package BarracksWars_The_Commands_Strike_Back.core.commands;

import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class CommandPattern {
    public static Command getCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        try {
            String className = data[0].substring(0, 1).toUpperCase() + data[0].substring(1);
            Class classCommand = Class.forName("BarracksWars_The_Commands_Strike_Back.core.commands." + className + "Command");
            try {
                Constructor constructorCommand = classCommand.getConstructor(String[].class, Repository.class, UnitFactory.class);
                return (Command) constructorCommand.newInstance(data, repository, unitFactory);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Invalid command.");
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Invalid command.");
            } catch (InstantiationException e) {
                throw new IllegalArgumentException("Invalid command.");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("Invalid command.");
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid command.");
        }
    }
}
