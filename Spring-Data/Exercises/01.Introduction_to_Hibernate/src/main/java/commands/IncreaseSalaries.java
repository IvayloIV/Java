package commands;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class IncreaseSalaries implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        final List<String> departments = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");

        Query query = entityManager
                .createQuery("UPDATE Employee e " +
                        "SET e.salary = e.salary * 1.12 " +
                        "WHERE e.department.id in " +
                        "   (SELECT d.id FROM Department d " +
                        "     WHERE d.departmentName IN :departments)");

        query.setParameter("departments", departments);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();

        TypedQuery<Employee> employeesQuery = entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.departmentName in :departments", Employee.class);

        employeesQuery.setParameter("departments", departments);
        employeesQuery.getResultStream()
                .forEach(e -> System.out.printf("%s %s ($%.2f)\n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()));
    }
}
