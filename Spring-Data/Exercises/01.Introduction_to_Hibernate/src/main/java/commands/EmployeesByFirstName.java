package commands;

import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.*;

public class EmployeesByFirstName implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String firstNameStart = br.readLine();

            TypedQuery<Employee> query = entityManager
                    .createQuery("SELECT e FROM Employee e " +
                            "WHERE e.firstName LIKE :firstNameStart", Employee.class);

            query.setParameter("firstNameStart", firstNameStart + "%");
            for (Employee employee : query.getResultList()) {
                bw.write(String.format("%s %s - %s ($%.2f)",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getJobTitle(),
                            employee.getSalary()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
