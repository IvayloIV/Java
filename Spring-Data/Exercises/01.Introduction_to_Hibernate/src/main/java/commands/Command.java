package commands;

import javax.persistence.EntityManager;

public interface Command {

    public void execute(EntityManager entityManager);
}
