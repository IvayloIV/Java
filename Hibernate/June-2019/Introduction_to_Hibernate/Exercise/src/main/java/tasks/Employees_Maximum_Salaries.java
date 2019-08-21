package tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Employees_Maximum_Salaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<Object[]> result = em.createNativeQuery("SELECT d.name, SUM(e.salary) salary FROM departments d\n" +
                "JOIN employees e\n" +
                "ON d.manager_id = e.employee_id\n" +
                "GROUP BY d.name\n" +
                "HAVING salary NOT BETWEEN 30000 AND 70000;")
            .getResultList();

        result.forEach(e -> System.out.printf("%s - %s%n", e[0], e[1]));
        em.getTransaction().commit();
    }
}
