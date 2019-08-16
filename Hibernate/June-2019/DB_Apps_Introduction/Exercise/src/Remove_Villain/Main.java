package Remove_Villain;

import DbConnection.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static  final String GET_VILLAIN = "SELECT name FROM villains\n" +
            "WHERE id = ?";

    private static  final String REMOVE_VILLAIN_BY_ID = "DELETE FROM villains\n" +
            "WHERE id = ?";

    private static final String RELEASE_MINIONS = "DELETE FROM minions_villains\n" +
            "WHERE villain_id = ?";

    private static final String GET_MINIONS_COUNT = "SELECT COUNT(*) count_minions FROM minions_villains\n" +
            "WHERE villain_id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int villainId = Integer.parseInt(sc.nextLine());

        Connection connection = DataBase.getConnection();

        PreparedStatement getVillainStmt = connection.prepareStatement(GET_VILLAIN);
        getVillainStmt.setInt(1, villainId);
        ResultSet resultVillain = getVillainStmt.executeQuery();

        if (!resultVillain.next()) {
            System.out.println("No such villain was found");
            return;
        }

        PreparedStatement getCountMinionsStmt = connection.prepareStatement(GET_MINIONS_COUNT);
        getCountMinionsStmt.setInt(1, villainId);
        ResultSet resultCount = getCountMinionsStmt.executeQuery();
        resultCount.next();

        PreparedStatement releaseMinionsStmt = connection.prepareStatement(RELEASE_MINIONS);
        releaseMinionsStmt.setInt(1, villainId);
        releaseMinionsStmt.executeUpdate();

        PreparedStatement removeVillainStmt = connection.prepareStatement(REMOVE_VILLAIN_BY_ID);
        removeVillainStmt.setInt(1, villainId);
        removeVillainStmt.executeUpdate();

        System.out.println(resultVillain.getString("name") + " was deleted");
        System.out.println(resultCount.getString("count_minions") + " minions released");
    }
}
