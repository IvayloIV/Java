package BarracksWars_The_Commands_Strike_Back.core;

import BarracksWars_The_Commands_Strike_Back.core.commands.Command;
import BarracksWars_The_Commands_Strike_Back.core.commands.CommandPattern;
import BarracksWars_The_Commands_Strike_Back.interfaces.Repository;
import BarracksWars_The_Commands_Strike_Back.interfaces.Runnable;
import BarracksWars_The_Commands_Strike_Back.interfaces.UnitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {

	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String result = interpretCommand(data);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String interpretCommand(String[] data)  {
		Command command = CommandPattern.getCommand(data, this.repository, this.unitFactory);
		return command.execute();
	}
}
