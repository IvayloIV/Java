package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {
    @Column
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    @NotNull
    private Town town;

    @OneToMany(mappedBy = "branch", targetEntity = Product.class)
    private Set<Product> products;

    @OneToMany(mappedBy = "branch", targetEntity = Employee.class)
    private Set<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
