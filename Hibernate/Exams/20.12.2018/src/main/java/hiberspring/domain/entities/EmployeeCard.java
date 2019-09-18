package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard extends BaseEntity {
    @Column(unique = true)
    @NotNull
    private String number;

    @OneToOne(mappedBy = "employeeCard", targetEntity = Employee.class)
    private Employee employees;

    public EmployeeCard() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }
}
