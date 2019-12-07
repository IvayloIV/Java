package metubev3.service;

import metubev3.domain.enums.TubeStatus;
import metubev3.domain.models.services.TubeServiceModel;

import java.util.List;

public interface TubeService {
    public boolean save(TubeServiceModel tubeServiceModel, String uploader);

    public List<TubeServiceModel> getAll();

    public List<TubeServiceModel> getByUsername(String username);

    public void increaseViews(TubeServiceModel tube);

    public TubeServiceModel getById(String tubeId);

    public List<TubeServiceModel> getAllWithStatus(TubeStatus status);

    public void changeStatus(String tubeId, TubeStatus status);
}
