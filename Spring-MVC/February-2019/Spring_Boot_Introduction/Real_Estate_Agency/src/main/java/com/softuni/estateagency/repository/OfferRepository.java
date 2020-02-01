package com.softuni.estateagency.repository;

import com.softuni.estateagency.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    @Query("SELECT o FROM Offer o " +
            "WHERE o.apartmentType = :apartmentType " +
                "AND o.apartmentRent + (o.apartmentRent * o.agencyCommission / 100) <= :apartmentBudget")
    public List<Offer> getByApartmentTypeAndBudget(@Param("apartmentType") String apartmentType,
                                            @Param("apartmentBudget") BigDecimal apartmentBudget);
}
