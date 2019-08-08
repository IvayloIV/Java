package BarracksWars_The_Commands_Strike_Back.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
