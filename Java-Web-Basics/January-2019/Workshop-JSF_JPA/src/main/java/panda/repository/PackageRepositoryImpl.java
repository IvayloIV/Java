package panda.repository;

import panda.domain.entities.Package;
import panda.domain.enums.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {

    private EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Package entity) {
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
    public Package getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT p FROM Package p " +
                            "WHERE p.id = :id", Package.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Package> getAll() {
        return this.entityManager
                .createQuery("SELECT p FROM Package p", Package.class)
                .getResultList();
    }

    @Override
    public Long count() {
        return this.entityManager
                .createQuery("SELECT count(p) FROM Package p", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Package> getAllByStatus(Status status) {
        return this.entityManager
                .createQuery("SELECT p FROM Package p " +
                            "WHERE p.status = :status", Package.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public void updatePackage(Package entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }
}
