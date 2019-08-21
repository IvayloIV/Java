package tasks;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Addresses_with_Employee_Count {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Address> resultList = em.createQuery("FROM Address ORDER BY size(employees) DESC, town_id ASC", Address.class)
                .setMaxResults(10)
                .getResultList();

        resultList.forEach(a -> System.out.println(String.format("%s, %s - %d employees",
            a.getText(),
            a.getTown().getName(),
            a.getEmployees().size())));

        em.getTransaction().commit();
    }
}
