package models.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient {

    public Lavender() {
        super("Lavender", 2.0);
    }
}
