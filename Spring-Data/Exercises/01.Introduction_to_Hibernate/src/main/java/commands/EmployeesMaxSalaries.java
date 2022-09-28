package commands;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

public class EmployeesMaxSalaries implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<Object[]> query = entityManager
                .createQuery("SELECT e.department.departmentName, " +
                        "   max(e.salary) FROM Employee e " +
                        "WHERE e.salary NOT BETWEEN :outOfRangeMin AND :outOfRangeMax " +
                        "GROUP BY e.department.departmentName " +
                        "ORDER BY max(e.salary)", Object[].class);

        query.setParameter("outOfRangeMin", new BigDecimal(30000));
        query.setParameter("outOfRangeMax", new BigDecimal(70000));

        query.getResultStream().forEach(e -> System.out.printf("%s - %.2f\n", e[0], e[1]));
    }
}
