package com.example.mapping.entries;

import java.util.List;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> employees;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s | Employees: %d%n",
                this.getFirstName(),
                this.getLastName(),
                this.employees.size()));

        for (EmployeeDto employeeDto : this.getEmployees()) {
            builder.append(employeeDto).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
