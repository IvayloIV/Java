package Company_Roster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Department implements Comparable<Department> {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public double getAvgSalary() {
        double avgSum =  this.employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, (a, b) -> a + b);
        return avgSum / employees.size();
    }

    @Override
    public String toString() {
        String text = "";
        text += "Highest Average Salary: " + this.name + System.lineSeparator();
        List<Employee> sortedEmployees = this.employees.stream().sorted().collect(Collectors.toList());
        for (Employee employee : sortedEmployees) {
            text += employee.toString() + System.lineSeparator();
        }

        return text;
    }

    @Override
    public int compareTo(Department d) {
        double aAvgSalary = this.getAvgSalary();
        double bAvgSalary = d.getAvgSalary();

        if (aAvgSalary > bAvgSalary) {
            return -1;
        } else if (aAvgSalary < bAvgSalary) {
            return 1;
        } else {
            return 0;
        }
    }
}
