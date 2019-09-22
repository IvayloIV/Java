package com.example.intro.services;

import com.example.intro.domain.entities.Offer;
import com.example.intro.domain.models.OfferFindBindingModel;
import com.example.intro.domain.models.OfferRegisterBindingModel;
import com.example.intro.repositories.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final javax.validation.Validator validator;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, Validator validator) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.findAll();
    }

    @Override
    public void createOffer(OfferRegisterBindingModel offerRegisterBindingModel) {
        if (this.validator.validate(offerRegisterBindingModel).size() != 0) {
            throw new IllegalArgumentException("Incorrect data.");
        }

        Offer offer = this.modelMapper.map(offerRegisterBindingModel, Offer.class);
        this.offerRepository.save(offer);
    }

    @Override
    public void findOffer(OfferFindBindingModel offerFindBindingModel) {
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Invalid data.");
        }

        List<Offer> offers = this.offerRepository.findByApartmentTypeAndCommission(
                offerFindBindingModel.getFamilyApartmentType(),
                offerFindBindingModel.getFamilyBudget()
        );

        if (offers.size() == 0) {
            throw new IllegalArgumentException("Offer not exist.");
        }
        this.offerRepository.delete(offers.get(0));
    }
}
