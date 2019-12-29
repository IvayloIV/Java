package fdmc.service;

import fdmc.domain.model.service.CatServiceModel;

import java.util.List;

public interface CatService {
    public boolean save(CatServiceModel catServiceModel);

    public List<CatServiceModel> getAll();
}
