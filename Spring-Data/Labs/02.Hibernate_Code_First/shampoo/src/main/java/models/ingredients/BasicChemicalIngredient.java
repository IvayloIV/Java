package models.ingredients;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {

    @Column(name = "chemical_formula")
    private String chemicalFormula;

    public BasicChemicalIngredient() {
    }

    public BasicChemicalIngredient(String name, Double price, String chemicalFormula) {
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
