package models.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NT")
public class Nettle extends BasicIngredient {

    public Nettle() {
        super("Nettle", 6.12);
    }
}
