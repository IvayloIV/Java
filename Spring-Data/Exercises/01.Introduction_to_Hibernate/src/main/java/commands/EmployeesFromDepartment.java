package commands;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployeesFromDepartment implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<Employee> query = entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = :departmentName " +
                        "ORDER BY e.salary, e.id", Employee.class);

        query.setParameter("departmentName", "Research and Development");
        query.getResultStream()
            .forEach(e -> System.out.printf("%s %s from %s - $%.2f\n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getDepartmentName(),
                        e.getSalary()));
    }
}
