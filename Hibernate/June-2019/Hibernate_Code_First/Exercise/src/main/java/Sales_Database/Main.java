package Sales_Database;

import Sales_Database.entities.Customer;
import Sales_Database.entities.Product;
import Sales_Database.entities.Sale;
import Sales_Database.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sales");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Product product = new Product("Apple", 32, BigDecimal.valueOf(2323.3));
        Customer customer = new Customer("Pesho", "pesho@abv.bg", "323");
        StoreLocation storeLocation = new StoreLocation("Ivanovo");

        em.persist(product);
        em.persist(customer);
        em.persist(storeLocation);

        Sale sale = new Sale(product, customer, storeLocation, Date.valueOf("2018-03-04"));
        em.persist(sale);

        em.getTransaction().commit();
    }
}
