import models.WizardDeposits;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager entityManager = enf.createEntityManager();

        WizardDeposits wd = new WizardDeposits();
        wd.setAge(44);
        wd.setLastName("Ivanov");

        entityManager.getTransaction().begin();
        entityManager.persist(wd);
        entityManager.getTransaction().commit();

        entityManager.close();
        enf.close();
    }
}
