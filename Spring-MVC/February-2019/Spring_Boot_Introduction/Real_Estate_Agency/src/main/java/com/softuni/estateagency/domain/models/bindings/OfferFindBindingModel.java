package com.softuni.estateagency.domain.models.bindings;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferFindBindingModel {

    @DecimalMin(value = "0.01")
    @NotNull
    private BigDecimal budget;

    @NotEmpty
    @NotNull
    private String apartmentType;

    @NotEmpty
    @NotNull
    private String familyName;

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
