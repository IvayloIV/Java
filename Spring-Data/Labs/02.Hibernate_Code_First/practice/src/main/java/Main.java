import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Cat cat = new Cat();
        cat.setName("Noni");

        Leg leg1 = new Leg();
        leg1.setHeight(34);
        Leg leg2 = new Leg();
        leg2.setHeight(54);

        cat.getLegs().add(leg1);
        cat.getLegs().add(leg2);

        em.persist(cat);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
