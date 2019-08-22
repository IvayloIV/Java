package Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {
    private static final BigDecimal PRICE = new BigDecimal("0.59");

    private static final String NAME = "Ammonium Chloride";

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }
}
