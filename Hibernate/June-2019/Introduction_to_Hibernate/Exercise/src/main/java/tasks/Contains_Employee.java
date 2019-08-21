package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Contains_Employee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Employee employee = em.createQuery("FROM Employee WHERE CONCAT(firstName, ' ', lastName) = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException e){
            System.out.println("No");
        }

        em.getTransaction().commit();
    }
}
