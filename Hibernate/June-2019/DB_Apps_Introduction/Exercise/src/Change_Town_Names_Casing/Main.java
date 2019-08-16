package Change_Town_Names_Casing;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String updateTownsQuery = "UPDATE towns\n" +
            "SET name = UPPER(name)\n" +
            "WHERE country = ?";

    private static final String getChangedTownsQuery = "SELECT name FROM towns\n" +
            "WHERE country = ?";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String townName = sc.nextLine();
        Connection connection = DataBase.getConnection();

        PreparedStatement updateStmt = connection.prepareStatement(updateTownsQuery);
        updateStmt.setString(1, townName);
        updateStmt.executeUpdate();

        List<String> changedTowns = new ArrayList<>();
        PreparedStatement getTownsStmt = connection.prepareStatement(getChangedTownsQuery);
        getTownsStmt.setString(1, townName);
        ResultSet resultSet = getTownsStmt.executeQuery();

        while (resultSet.next()) {
            changedTowns.add(resultSet.getString("name"));
        }

        if (changedTowns.size() == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.println(changedTowns.size() + " town names were affected.");
            System.out.println("[" + String.join(", ", changedTowns) + "]");
        }
    }
}
