package BarracksWars_The_Commands_Strike_Back.interfaces;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	void removeUnit(String unitType);
}
