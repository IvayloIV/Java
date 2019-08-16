package Increase_Age_Stored_Procedure;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String INCREASE_MINION_AGE_QUERY = "CALL usp_get_older(?);";

    private static final String GET_MINION = "SELECT name, age FROM minions\n" +
            "WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int minionId = Integer.parseInt(sc.nextLine());

        Connection connection = DataBase.getConnection();
        PreparedStatement stmtIncreaseAge = connection.prepareStatement(INCREASE_MINION_AGE_QUERY);
        stmtIncreaseAge.setInt(1, minionId);
        stmtIncreaseAge.executeQuery();

        PreparedStatement stmtGetMinion = connection.prepareStatement(GET_MINION);
        stmtGetMinion.setInt(1, minionId);
        ResultSet resultSet = stmtGetMinion.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));
    }
}
