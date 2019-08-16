package Print_All_Minion_Names;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String query = "SELECT name FROM minions;";

    public static void main(String[] args) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        for (int i = 0; i < names.size() / 2; i++) {
            System.out.println(names.get(i));
            System.out.println(names.get(names.size() - i - 1));
        }

        if (names.size() % 2 == 1) {
            System.out.println(names.get(names.size() / 2));
        }
    }
}
