import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("bills_payment_system");
        EntityManager entityManager = enf.createEntityManager();

        entityManager.close();
        enf.close();
    }
}
