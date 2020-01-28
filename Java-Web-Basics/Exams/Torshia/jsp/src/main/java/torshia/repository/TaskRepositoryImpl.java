package torshia.repository;

import torshia.domain.entities.Task;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private EntityManager entityManager;

    @Inject
    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Task entity) {
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
    public List<Task> getAll() {
        return this.entityManager
                .createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();
    }

    @Override
    public Task getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT t FROM Task t " +
                            "WHERE t.id = :id", Task.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(t) FROM Task t", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Task> getTaskWithReport(Boolean isReported) {
        return this.entityManager
                .createQuery("SELECT t FROM Task t " +
                        "WHERE t.isReported = :isReported", Task.class)
                .setParameter("isReported", isReported)
                .getResultList();
    }

    @Override
    public Boolean update(Task task) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(task);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
