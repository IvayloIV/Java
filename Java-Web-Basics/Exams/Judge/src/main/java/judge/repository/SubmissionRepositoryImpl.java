package judge.repository;

import judge.domain.entities.Submission;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class SubmissionRepositoryImpl implements SubmissionRepository {

    private EntityManager entityManager;

    @Inject
    public SubmissionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Submission save(Submission entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Submission> getAll() {
        return this.entityManager
                .createQuery("SELECT s FROM Submission s", Submission.class)
                .getResultList();
    }

    @Override
    public Submission getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT s FROM Submission s " +
                            "WHERE s.id = :id", Submission.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(s) FROM Submission s", Long.class)
                .getSingleResult();
    }
}
