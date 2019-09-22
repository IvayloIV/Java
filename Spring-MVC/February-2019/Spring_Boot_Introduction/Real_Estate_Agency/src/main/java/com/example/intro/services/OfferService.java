package com.example.intro.services;

import com.example.intro.domain.entities.Offer;
import com.example.intro.domain.models.OfferFindBindingModel;
import com.example.intro.domain.models.OfferRegisterBindingModel;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();

    void createOffer(OfferRegisterBindingModel offerRegisterBindingModel);

    void findOffer(OfferFindBindingModel offerFindBindingModel);
}
