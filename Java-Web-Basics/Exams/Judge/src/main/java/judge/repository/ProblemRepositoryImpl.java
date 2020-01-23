package judge.repository;

import judge.domain.entities.Problem;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProblemRepositoryImpl implements ProblemRepository {

    private EntityManager entityManager;

    @Inject
    public ProblemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Problem save(Problem entity) {
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
    public List<Problem> getAll() {
        return this.entityManager
                .createQuery("SELECT p FROM Problem p", Problem.class)
                .getResultList();
    }

    @Override
    public Problem getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT p FROM Problem p " +
                            "WHERE p.id = :id", Problem.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(p) FROM Problem p", Long.class)
                .getSingleResult();
    }
}
