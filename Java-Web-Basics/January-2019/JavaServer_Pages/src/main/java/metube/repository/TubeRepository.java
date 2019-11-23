package metube.repository;

import metube.domain.entity.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
    public Tube findByName(String name);
}
