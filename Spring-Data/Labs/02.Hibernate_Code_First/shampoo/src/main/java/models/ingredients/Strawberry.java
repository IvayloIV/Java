package models.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SB")
public class Strawberry extends BasicIngredient {

    public Strawberry() {
        super("Strawberry", 4.85);
    }
}
