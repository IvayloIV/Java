package models.ingredients;

public interface ChemicalIngredient extends Ingredient {

    public String getChemicalFormula();

    public void setChemicalFormula(String chemicalFormula);
}
