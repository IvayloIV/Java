package Gringotts_Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("wizard_deposits");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        WizardDeposits wizardDeposits = new WizardDeposits();
        em.getTransaction().commit();
    }
}
