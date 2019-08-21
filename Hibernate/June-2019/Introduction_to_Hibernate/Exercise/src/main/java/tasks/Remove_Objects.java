package tasks;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Remove_Objects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Town> resultList = em.createQuery("FROM Town WHERE LENGTH(name) > 5", Town.class)
                .getResultList();

        resultList.forEach(a -> {
            a.setName(a.getName().toLowerCase());
            em.persist(a);
        });
        em.getTransaction().commit();
    }
}
