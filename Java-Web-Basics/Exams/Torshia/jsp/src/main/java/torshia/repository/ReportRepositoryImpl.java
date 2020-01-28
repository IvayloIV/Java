package torshia.repository;

import torshia.domain.entities.Report;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    private EntityManager entityManager;

    @Inject
    public ReportRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Report entity) {
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
    public List<Report> getAll() {
        return this.entityManager
                .createQuery("SELECT r FROM Report r", Report.class)
                .getResultList();
    }

    @Override
    public Report getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT r FROM Report r " +
                            "WHERE r.id = :id", Report.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(r) FROM Report r", Long.class)
                .getSingleResult();
    }
}
