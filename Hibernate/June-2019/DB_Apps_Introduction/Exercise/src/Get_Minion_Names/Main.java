package Get_Minion_Names;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String query = "SELECT \n" +
            "    v.name AS villains_name,\n" +
            "    m.name AS minion_name,\n" +
            "    m.age AS minion_age\n" +
            "FROM villains AS v\n" +
            "JOIN minions_villains AS mv\n" +
            "ON v.id = mv.villain_id\n" +
            "JOIN minions AS m\n" +
            "ON m.id = mv.minion_id\n" +
            "WHERE v.id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = DataBase.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);

        int id = Integer.parseInt(sc.nextLine());
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        int count = 0;
        while (resultSet.next()) {
            if (count == 0) {
                System.out.println("Villain: " + resultSet.getString("villains_name"));
            }
            System.out.println(String.format("%d. %s %s",
                    ++count,
                    resultSet.getString("minion_name"),
                    resultSet.getString("minion_age")));
        }

        if (count == 0) {
            System.out.printf("No villain with ID %d exists in the database.%n", id);
        }
    }
}
