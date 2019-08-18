package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", props);
    }

    public static Connection getConnection() {
        return connection;
    }
}
