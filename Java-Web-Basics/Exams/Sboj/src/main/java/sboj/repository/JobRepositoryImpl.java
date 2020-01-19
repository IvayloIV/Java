package sboj.repository;

import sboj.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {

    private EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(JobApplication entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<JobApplication> getAll() {
        return this.entityManager
                .createQuery("SELECT j FROM JobApplication j", JobApplication.class)
                .getResultList();
    }

    @Override
    public JobApplication getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT j FROM JobApplication j " +
                            "WHERE j.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(j) FROM JobApplication j", Long.class)
                .getSingleResult();
    }

    @Override
    public boolean removeById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager
                    .createQuery("DELETE FROM JobApplication j " +
                                    "WHERE j.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
