package Accessing_Database;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        double salary = Double.parseDouble(scanner.nextLine());
        stmt.setDouble(1, salary);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %s%n",
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        }
    }
}
