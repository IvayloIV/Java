package spring.demo.shampoos.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Data
@NamedQueries(
    @NamedQuery(name = "Shampoo.findIngredientsPriceByBrand",
        query = "SELECT sum(i.price) " +
                " FROM Shampoo s" +
                " JOIN s.ingredients i " +
                " WHERE s.brand = :brand")
)
public class Shampoo extends BaseEntity {

    private String brand;

    private Double price;

    @Enumerated(EnumType.ORDINAL)
    private Size size;

    @ManyToOne
    private Label label;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "shampoos_ingredients",
        joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shampoo)) return false;
        if (!super.equals(o)) return false;
        Shampoo shampoo = (Shampoo) o;
        return Objects.equals(brand, shampoo.brand) &&
                Objects.equals(price, shampoo.price) &&
                size == shampoo.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, price, size);
    }

    @Override
    public String toString() {
        return "Shampoo{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", size=" + size +
                "} " + super.toString();
    }
}
