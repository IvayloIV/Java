package Increase_Minions_Age;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String updateMinions = "UPDATE minions m\n" +
            "SET m.age = m.age + 1,\n" +
            " m.name = LOWER(m.name)\n" +
            "WHERE m.id = ?";

    private static final String getMinions = "SELECT name, age FROM minions";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Connection connection = DataBase.getConnection();

        for (Integer num : nums) {
            PreparedStatement stmtUpdate = connection.prepareStatement(updateMinions);
            stmtUpdate.setInt(1, num);
            stmtUpdate.execute();
        }

        PreparedStatement getMinionsStmt = connection.prepareStatement(getMinions);
        ResultSet resultSet = getMinionsStmt.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));
        }
    }
}
