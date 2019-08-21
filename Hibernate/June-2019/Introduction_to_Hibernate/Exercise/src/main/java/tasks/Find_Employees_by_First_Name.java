package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Find_Employees_by_First_Name {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        String pattern = sc.nextLine() + "%";
        List<Employee> employees = em.createQuery("FROM Employee WHERE first_name LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        employees.forEach(e -> System.out.println(String.format("%s %s - %s - ($%s)",
                e.getFirstName(),
                e.getLastName(),
                e.getJobTitle(),
                e.getSalary())));

        em.getTransaction().commit();
    }
}
