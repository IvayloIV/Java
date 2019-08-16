package Add_Minion;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String querySearchTown = "SELECT * FROM towns\n" +
            "WHERE name = ?";

    private static final String addTownQuery = "INSERT INTO towns(name)\n" +
            "VALUE (?)";

    private static final String querySearchVillain = "SELECT * FROM villains\n" +
            "WHERE name = ?";

    private static final String addVillainQuery = "INSERT INTO villains(name, evilness_factor)\n" +
            "VALUE (?, 'evil')";

    private static final String addMinionQuery = "INSERT INTO minions(name, age, town_id)\n" +
            "VALUE (?, ?, ?)";

    private static final String getMinionQuery = "SELECT * FROM minions\n" +
            "WHERE name = ?";

    private static final String addMinionVillainQuery = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
            "VALUE (?, ?)";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = DataBase.getConnection();

        String[] minionTokens = sc.nextLine().split("\\s+");
        String minionName = minionTokens[1];
        int age = Integer.parseInt(minionTokens[2]);
        String minionTown = minionTokens[3];

        String[] villainTokens = sc.nextLine().split("\\s+");
        String villainName = villainTokens[1];

        ResultSet resultTown = getSearchedTown(connection, minionTown);
        if (!resultTown.next()) {
            addNewTown(connection, minionTown);
            System.out.printf("Town %s was added to the database.%n", minionTown);
            resultTown = getSearchedTown(connection, minionTown);
            resultTown.next();
        }

        ResultSet resultVillain = getSearchedVillain(connection, villainName);
        if (!resultVillain.next()) {
            addNewVillain(connection, villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
            resultVillain = getSearchedVillain(connection, villainName);
            resultVillain.next();
        }

        addNewMinion(connection, minionName, age, resultTown);
        String minionId = getMinionIdByName(connection, minionName);
        addMinionVillain(connection, resultVillain, minionId);
        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
    }

    private static void addMinionVillain(Connection connection, ResultSet resultVillain, String minionId) throws SQLException {
        PreparedStatement addMinionVillainStmt = connection.prepareStatement(addMinionVillainQuery);
        addMinionVillainStmt.setString(1, minionId);
        addMinionVillainStmt.setString(2, resultVillain.getString("id"));
        addMinionVillainStmt.executeUpdate();
    }

    private static String getMinionIdByName(Connection connection, String minionName) throws SQLException {
        PreparedStatement searchForMinionStmt = connection.prepareStatement(getMinionQuery);
        searchForMinionStmt.setString(1, minionName);
        ResultSet resultSet = searchForMinionStmt.executeQuery();
        resultSet.next();
        return resultSet.getString("id");
    }

    private static void addNewMinion(Connection connection, String minionName, int age, ResultSet resultTown) throws SQLException {
        PreparedStatement addMinionStmt = connection.prepareStatement(addMinionQuery);
        addMinionStmt.setString(1, minionName);
        addMinionStmt.setInt(2, age);
        addMinionStmt.setString(3, resultTown.getString("id"));
        addMinionStmt.executeUpdate();
    }

    private static ResultSet getSearchedTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement searchTownStmt = connection.prepareStatement(querySearchTown);
        searchTownStmt.setString(1, minionTown);
        return searchTownStmt.executeQuery();
    }

    private static void addNewTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement createTownStmt = connection.prepareStatement(addTownQuery);
        createTownStmt.setString(1, minionTown);
        createTownStmt.executeUpdate();
    }

    private static ResultSet getSearchedVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement searchVillainStmt = connection.prepareStatement(querySearchVillain);
        searchVillainStmt.setString(1, villainName);
        return searchVillainStmt.executeQuery();
    }

    private static void addNewVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement createVillainStmt = connection.prepareStatement(addVillainQuery);
        createVillainStmt.setString(1, villainName);
        createVillainStmt.executeUpdate();
    }
}
