import models.Car;
import models.Car_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cars");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(new Car("Opel"));
        em.getTransaction().commit();

        Car car = em.find(Car.class, 1L);
        System.out.println(car);

        em.createQuery("SELECT c FROM Car c", Car.class)
                .setMaxResults(3)
                .getResultList()
                .forEach(System.out::println);

        em.createNativeQuery("SELECT * FROM cars c", Car.class)
                .setMaxResults(3)
                .getResultList()
                .forEach(System.out::println);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        Predicate equal = criteriaBuilder.equal(root.get(Car_.brand), "Opel");
        criteriaQuery.select(root.get(Car_.BRAND)).where(equal);
        em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }
}
