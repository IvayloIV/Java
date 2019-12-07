package metubev3.repository;

import metubev3.domain.entities.Tube;
import metubev3.domain.enums.TubeStatus;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Tube entity) {
        this.entityManager.getTransaction().begin();

        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager
                .createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
    }

    @Override
    public Tube findById(String id) {
        this.entityManager.getTransaction().begin();

        try {
            return this.entityManager
                    .createQuery("SELECT t FROM Tube t " +
                            "WHERE t.id = :id", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Tube> findByUsername(String username) {
        return this.entityManager
                .createQuery("SELECT t FROM Tube t " +
                        "WHERE t.uploader.username = :username", Tube.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public void increaseViews(Tube tube) {
        this.entityManager.getTransaction().begin();

        try {
            this.entityManager.merge(tube);
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Tube> findAllWithStatus(TubeStatus status) {
        return this.entityManager
                .createQuery("SELECT t FROM Tube t " +
                        "WHERE t.status = :status", Tube.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public void changeStatus(String tubeId, TubeStatus status) {
        this.entityManager.getTransaction().begin();

        try {
            this.entityManager
                    .createQuery("UPDATE Tube t " +
                            "SET t.status = :status " +
                            "WHERE t.id = :id")
                    .setParameter("status", status)
                    .setParameter("id", tubeId)
                    .executeUpdate();
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }
}
