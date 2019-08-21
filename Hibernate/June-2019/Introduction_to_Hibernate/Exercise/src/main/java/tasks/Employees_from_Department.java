package tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Employees_from_Department {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Object[]> resultList = em.createNativeQuery("SELECT e.first_name, e.last_name, d.name AS department_name, e.salary FROM employees e\n" +
                "JOIN departments d\n" +
                "ON e.department_id = d.department_id\n" +
                "WHERE d.name = 'Research and Development'\n" +
                "ORDER BY e.salary ASC, e.employee_id ASC")
                .getResultList();

        resultList.forEach(e -> System.out.println(String.format("%s %s from %s - $%s",
                e[0], e[1], e[2], e[3])));

        em.getTransaction().commit();
    }
}
