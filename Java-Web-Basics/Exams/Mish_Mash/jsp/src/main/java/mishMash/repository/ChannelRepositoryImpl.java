package mishMash.repository;

import mishMash.domain.entities.Channel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ChannelRepositoryImpl implements ChannelRepository {

    private EntityManager entityManager;

    @Inject
    public ChannelRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Channel entity) {
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
    public List<Channel> getAll() {
        return this.entityManager
                .createQuery("SELECT c FROM Channel c", Channel.class)
                .getResultList();
    }

    @Override
    public Channel getById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT c FROM Channel c " +
                            "WHERE c.id = :id", Channel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(c) FROM Channel c", Long.class)
                .getSingleResult();
    }

    @Override
    public boolean update(Channel channel) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(channel);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
