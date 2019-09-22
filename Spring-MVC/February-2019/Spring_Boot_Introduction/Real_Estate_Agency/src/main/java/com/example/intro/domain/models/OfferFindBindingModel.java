package com.example.intro.domain.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferFindBindingModel {
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal familyBudget;

    @NotNull
    @Size(min = 1)
    private String familyApartmentType;

    @NotNull
    @Size(min = 1)
    private String familyName;

    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
