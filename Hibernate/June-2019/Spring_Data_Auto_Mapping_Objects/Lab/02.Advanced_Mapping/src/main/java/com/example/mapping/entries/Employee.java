package com.example.mapping.entries;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private BigDecimal salary;
    private boolean isOnHoliday;
    private String address;
    private Employee manager;
    private List<Employee> employees;

    public Employee(String firstName, String lastName, LocalDate birthday, BigDecimal salary, boolean isOnHoliday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.salary = salary;
        this.isOnHoliday = isOnHoliday;
        this.address = address;
        this.employees = new ArrayList<>();
    }

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
