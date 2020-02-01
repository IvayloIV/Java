package com.softuni.estateagency.service;

import com.softuni.estateagency.domain.entities.Offer;
import com.softuni.estateagency.domain.models.bindings.OfferFindBindingModel;
import com.softuni.estateagency.domain.models.services.OfferServiceModel;
import com.softuni.estateagency.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private ModelMapper modelMapper;
    private OfferRepository offerRepository;
    private Validator validator;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, Validator validator) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.validator = validator;
    }

    @Override
    public void createOffer(OfferServiceModel offerServiceModel) {
        if (validator.validate(offerServiceModel).size() != 0) {
            throw new IllegalArgumentException("Invalid param");
        }

        this.offerRepository
                .save(this.modelMapper.map(offerServiceModel, Offer.class));
    }

    @Override
    public List<OfferServiceModel> getAll() {
        return this.offerRepository.findAll()
                    .stream()
                    .map(o -> this.modelMapper.map(o, OfferServiceModel.class))
                    .collect(Collectors.toList());
    }

    @Override
    public void removeByTypeAndBudget(OfferFindBindingModel offerFindBindingModel) {
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Invalid parameters!");
        }

        List<Offer> offers = this.offerRepository.getByApartmentTypeAndBudget(
                offerFindBindingModel.getApartmentType(), offerFindBindingModel.getBudget());

        if (offers.size() == 0) {
            throw new IllegalArgumentException("Apartment does not exist!");
        }

        this.offerRepository.delete(offers.get(0));
    }
}
