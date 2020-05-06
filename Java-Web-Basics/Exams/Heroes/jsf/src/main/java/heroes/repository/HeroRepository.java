package heroes.repository;

import heroes.domain.entities.Hero;

import java.util.List;

public interface HeroRepository extends GenericRepository<Hero, String> {

    public List<Hero> getAllByLevelDesc();

    public boolean removeById(String id);
}
