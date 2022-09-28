package commands;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RemoveObjects implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<Town> query = entityManager
                .createQuery("SELECT t FROM Town t " +
                        "WHERE length(t.name) > :townLength", Town.class);

        query.setParameter("townLength", 5);

        entityManager.getTransaction().begin();
        query.getResultList()
            .stream()
            .map(t -> {
                String name = t.getName().toLowerCase();
                t.setName(name);
                return t;
            })
            .forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }
}
