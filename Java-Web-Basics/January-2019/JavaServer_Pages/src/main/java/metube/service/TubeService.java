package metube.service;

import metube.domain.model.service.TubeServiceModel;

import java.util.List;

public interface TubeService {
    public void save(TubeServiceModel tubeServiceModel);

    public List<TubeServiceModel> findAll();

    public TubeServiceModel findByName(String name);
}
