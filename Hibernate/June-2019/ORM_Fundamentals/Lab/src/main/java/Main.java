import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Connector.createConnection("root", "1234", "soft_uni");
        Connection connector = Connector.getConnection();

        EntityManager<User> entityManager = new EntityManager<>(connector);

//        User user = new User("pesho", "12345", 253, Date.valueOf("2019-03-21"));
//        user.setId(2);
//        entityManager.persist(user);

        System.out.println(entityManager.findFirst(User.class).getUsername());
    }
}
