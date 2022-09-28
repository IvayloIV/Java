package models.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MN")
public class Mint extends BasicIngredient {

    public Mint() {
        super("Mint", 3.54);
    }
}
