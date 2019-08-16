package Diablo_Games;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);
        PreparedStatement stmt = connection.prepareStatement("SELECT u.user_name, u.first_name, u.last_name, COUNT(*) games FROM users AS u\n" +
                "JOIN users_games AS ug\n" +
                "ON ug.user_id = u.id\n" +
                "WHERE u.user_name = ?");

        String userName = sc.nextLine();
        stmt.setString(1, userName);

        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();
        if (resultSet.getString("user_name") == null) {
            System.out.println("No such user exists");
        } else {
            System.out.println("User: " + resultSet.getString("user_name"));
            System.out.println(String.format("%s %s has played %s games",
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("games")));
        }
    }
}
