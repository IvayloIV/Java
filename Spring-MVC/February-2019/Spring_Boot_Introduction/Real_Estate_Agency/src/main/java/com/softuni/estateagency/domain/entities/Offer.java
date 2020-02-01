package com.softuni.estateagency.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer extends BaseEntity {

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "apartment_rent")
    private BigDecimal apartmentRent;

    @NotNull
    @NotEmpty
    @Column(name = "apartment_type")
    private String apartmentType;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "agency_commission")
    private BigDecimal agencyCommission;

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
