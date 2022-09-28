package spring.demo.shampoos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@EqualsAndHashCode(callSuper = true)
@NamedQueries(
    @NamedQuery(name = "Ingredient.increasePrice",
        query = "UPDATE Ingredient i " +
                " SET i.price = i.price * (1 + (:percentage / 100.0))")
)
public class Ingredient extends BaseEntity {

    private String name;

    private Double price;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Shampoo> shampoos;

    @Override
    public String toString() {
        return name;
    }
}
