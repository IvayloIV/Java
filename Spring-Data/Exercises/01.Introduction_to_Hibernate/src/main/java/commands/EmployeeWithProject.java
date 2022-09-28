package commands;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.io.*;

public class EmployeeWithProject implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Integer employeeId = Integer.parseInt(br.readLine());

            Employee employee = entityManager.find(Employee.class, employeeId);
            bw.write(String.format("%s %s - %s",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getDepartmentName()));
            bw.newLine();

            for (Project project : employee.getProjects()) {
                bw.write("  " + project.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
