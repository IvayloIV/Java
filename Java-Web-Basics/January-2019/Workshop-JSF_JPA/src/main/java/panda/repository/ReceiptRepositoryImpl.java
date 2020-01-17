package panda.repository;

import panda.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {

    private EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Receipt entity) {
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
    public Receipt getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT r FROM Receipt r " +
                            "WHERE r.id = :id", Receipt.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Receipt> getAll() {
        return this.entityManager
                .createQuery("SELECT r FROM Receipt r", Receipt.class)
                .getResultList();
    }

    @Override
    public Long count() {
        return this.entityManager
                .createQuery("SELECT count(r) FROM Receipt r", Long.class)
                .getSingleResult();
    }
}
