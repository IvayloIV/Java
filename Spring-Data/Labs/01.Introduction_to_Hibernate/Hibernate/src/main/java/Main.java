import models.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        Car opel = new Car("Opel");

        try(SessionFactory sf = cfg.buildSessionFactory();
            Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(opel);
            session.getTransaction().commit();

            session.beginTransaction();
            session.evict(opel);
//            opel.setBrand("BMV");
//            session.merge(opel);
            session.getTransaction().commit();

            session.beginTransaction();
            Car car = session.find(Car.class, 1L);
            System.out.println(car);
            session.getTransaction().commit();

            session.beginTransaction();
            List<Car> cars = session.createQuery("FROM Car", Car.class).list();
            cars.forEach(System.out::println);
            session.getTransaction().commit();

            session.beginTransaction();
            Date lastProducedCar = session.createQuery("SELECT max(creationDate) FROM Car", Date.class)
                    .getSingleResult();
            System.out.println(lastProducedCar);
            session.getTransaction().commit();

            session.beginTransaction();
            Query<Car> query = session.createQuery("select c FROM Car c where c.brand = :brand", Car.class);
            query.setParameter("brand", "Opel");
            List<Car> opels = query.getResultList();
            opels.forEach(System.out::println);
            session.getTransaction().commit();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            Predicate equal = criteriaBuilder.equal(root.get("brand"), "BMV");
            criteriaQuery.select(root).where(equal);
            List<Car> opelCars = session.createQuery(criteriaQuery).getResultList();
            opelCars.forEach(System.out::println);
        }
    }
}
