package com.example.intro.repositories;

import com.example.intro.domain.entities.Offer;
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
            "   AND o.apartmentRent * (1 + o.agencyCommission / 100) <= :budget")
    List<Offer> findByApartmentTypeAndCommission(@Param("apartmentType") String apartmentType, @Param("budget") BigDecimal budget);
}
