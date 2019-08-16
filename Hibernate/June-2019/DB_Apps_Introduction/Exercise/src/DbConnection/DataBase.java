package DbConnection;

import java.sql.*;
import java.util.Properties;

public class DataBase {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        return DriverManager.getConnection("jdbc:mysql://localhost:3000/minions_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", props);
    }
}
