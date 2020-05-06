package heroes.service;

import heroes.domain.models.services.HeroServiceModel;

import java.util.List;

public interface HeroService {

    public boolean create(HeroServiceModel heroServiceModel);

    public List<HeroServiceModel> getAll();

    public List<HeroServiceModel> getAllByLevelDesc();

    public HeroServiceModel getById(String id);

    public boolean removeById(String id);
}
