package chushki.repository;

import chushki.domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;

    public ProductRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("chushka")
                .createEntityManager();
    }

    @Override
    public void save(Product entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Product findById(String id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.createQuery("select p from products p " +
                "WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();

        entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findAll() {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager.createQuery("select p from products p ", Product.class)
                .getResultList();

        entityManager.getTransaction().commit();
        return products;
    }

    @Override
    public Product findByName(String name) {
        entityManager.getTransaction().begin();
        Product product = entityManager.createQuery("select p from products p " +
                "WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();

        entityManager.getTransaction().commit();
        return product;
    }
}
