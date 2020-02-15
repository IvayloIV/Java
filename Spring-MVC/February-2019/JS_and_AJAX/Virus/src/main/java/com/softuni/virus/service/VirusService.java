package com.softuni.virus.service;

import com.softuni.virus.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {

    public boolean save(VirusServiceModel virusServiceModel);

    public List<VirusServiceModel> getAll();

    public void removeById(String id);

    public VirusServiceModel findById(String id);
}
