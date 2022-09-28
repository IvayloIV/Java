package models.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {

    public AmmoniumChloride() {
        super("Ammonium Chloride", 6.12, "NH4CL");
    }
}
