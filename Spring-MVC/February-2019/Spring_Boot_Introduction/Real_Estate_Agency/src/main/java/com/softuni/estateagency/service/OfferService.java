package com.softuni.estateagency.service;

import com.softuni.estateagency.domain.models.bindings.OfferFindBindingModel;
import com.softuni.estateagency.domain.models.services.OfferServiceModel;

import java.util.List;

public interface OfferService {

    public void createOffer(OfferServiceModel offerServiceModel);

    public List<OfferServiceModel> getAll();

    public void removeByTypeAndBudget(OfferFindBindingModel offerFindBindingModel);
}
