package metubev3.repository;

import metubev3.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metybev3")
                .createEntityManager();
    }

    @Override
    public boolean save(User entity) {
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
    public List<User> findAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();

        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u " +
                                    "WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public User login(User user) {
        this.entityManager.getTransaction().begin();

        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u " +
                            "WHERE u.username = :username AND " +
                            "   u.password = :password", User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public User findByUsername(String username) {
        this.entityManager.getTransaction().begin();

        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u " +
                            "WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();
        try {
            return this.entityManager
                    .createQuery("SELECT COUNT(*) FROM User ", Long.class)
                    .getSingleResult();
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }
}
