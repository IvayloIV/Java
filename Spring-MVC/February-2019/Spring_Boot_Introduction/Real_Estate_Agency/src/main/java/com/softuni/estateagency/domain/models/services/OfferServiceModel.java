package com.softuni.estateagency.domain.models.services;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferServiceModel {

    private String id;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal apartmentRent;

    @NotNull
    @NotEmpty
    private String apartmentType;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal agencyCommission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
