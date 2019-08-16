package Get_Villains_Names;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT v.name, COUNT(*) minions FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING minions > 15\n" +
                "ORDER BY minions DESC;");

        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getString("minions"));
        }
    }
}
