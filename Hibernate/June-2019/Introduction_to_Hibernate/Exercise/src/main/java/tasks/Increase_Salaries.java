package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Increase_Salaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees = em.createNativeQuery("SELECT * FROM employees e\n" +
                "JOIN departments d\n" +
                "ON d.department_id = e.department_id\n" +
                "WHERE d.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services');", Employee.class)
            .getResultList();

        employees.forEach(e -> {
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s (%s)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getSalary());
        });

        em.getTransaction().commit();
    }
}
