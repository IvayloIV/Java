package metubev3.repository;

import metubev3.domain.entities.Tube;
import metubev3.domain.enums.TubeStatus;

import java.util.List;

public interface TubeRepository extends GenericRepository<Tube, String> {
    public List<Tube> findByUsername(String username);

    public void increaseViews(String tubeId);

    public List<Tube> findAllWithStatus(TubeStatus status);

    public void changeStatus(String tubeId, TubeStatus status);
}
