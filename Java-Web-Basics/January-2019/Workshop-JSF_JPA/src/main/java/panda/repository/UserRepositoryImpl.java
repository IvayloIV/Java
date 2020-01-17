package panda.repository;

import panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(User entity) {
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
    public User getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u " +
                                "WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public Long count() {
        return this.entityManager
                .createQuery("SELECT count(u) FROM User u", Long.class)
                .getSingleResult();
    }

    @Override
    public User getByUsername(String username) {
        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u " +
                                "WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
