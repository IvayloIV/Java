package metube.repository;

import metube.domain.entity.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metybe")
                .createEntityManager();
    }

    @Override
    public void save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager.createQuery("select t from tube t", Tube.class)
                .getResultList();
    }


    @Override
    public Tube findById(String id) {
        return this.entityManager.createQuery("select t from tube t " +
                "where t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Tube findByName(String name) {
        return this.entityManager.createQuery("select t from tube t " +
                "where t.name = :name", Tube.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
