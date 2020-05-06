package heroes.repository;

import heroes.domain.entities.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public boolean save(Hero entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Hero> getAll() {
        return this.entityManager
                .createQuery("SELECT h FROM Hero h", Hero.class)
                .getResultList();
    }

    @Override
    public List<Hero> getAllByLevelDesc() {
        return this.entityManager
                .createQuery("SELECT h FROM Hero h " +
                                "ORDER BY h.level DESC", Hero.class)
                .getResultList();
    }

    @Override
    public Hero getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT h FROM Hero h " +
                            "WHERE h.id = :id", Hero.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(*) FROM Hero", Long.class)
                .getSingleResult();
    }

    @Override
    public boolean removeById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager
                    .createQuery("DELETE FROM Hero h " +
                            "WHERE h.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
