import commands.Command;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Engine {

    private EntityManager entityManager;
    private Command command;

    public Engine() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        entityManager = emf.createEntityManager();
    }

    public Engine(Command command) {
        this();
        this.command = command;
    }

    public void run() {
        command.execute(entityManager);
    }
}
