package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Get_Employee_with_Project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        int id = Integer.parseInt(sc.nextLine());
        Employee employee = em.createQuery("FROM Employee WHERE id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        List<Object> projectsName = em.createNativeQuery("SELECT p.name FROM employees_projects ep\n" +
                "JOIN projects p\n" +
                "ON p.project_id = ep.project_id\n" +
                "WHERE employee_id = ?1\n" +
                "ORDER BY p.name")
            .setParameter(1, id)
            .getResultList();

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        projectsName.forEach(a -> System.out.println("\t" + a));
        em.getTransaction().commit();
    }
}
