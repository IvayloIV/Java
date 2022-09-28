package commands;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

public class EmployeesWithSalary implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<String> query = entityManager
                .createQuery("SELECT e.firstName FROM Employee e " +
                        "WHERE e.salary > :minSalary", String.class);

        query.setParameter("minSalary", new BigDecimal(50000));
        query.getResultStream().forEach(System.out::println);
    }
}
