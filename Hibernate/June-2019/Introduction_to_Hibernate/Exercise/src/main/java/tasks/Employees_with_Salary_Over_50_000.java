package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Employees_with_Salary_Over_50_000 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Employee> resultList = em.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();


        resultList.forEach(e -> System.out.println(e.getFirstName()));
        em.getTransaction().commit();
    }
}
