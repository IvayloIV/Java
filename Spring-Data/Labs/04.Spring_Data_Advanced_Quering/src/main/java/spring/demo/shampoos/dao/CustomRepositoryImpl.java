package spring.demo.shampoos.dao;

import org.springframework.transaction.annotation.Transactional;
import spring.demo.shampoos.models.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ingredient> findAllWithEvenId() {
        TypedQuery<Ingredient> query = entityManager
                .createQuery("SELECT i FROM Ingredient i WHERE mod(i.id, 2) = 0", Ingredient.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateEvenIdPrice(double price) {
        Query query = entityManager
                .createQuery("UPDATE Ingredient i SET i.price = i.price * :price WHERE mod(i.id, 2) = 0");

        query.setParameter("price", price);
        query.executeUpdate();
    }
}
