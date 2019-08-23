package Hospital_Database;

import Hospital_Database.entities.Diagnose;
import Hospital_Database.entities.Medicament;
import Hospital_Database.entities.Patient;
import Hospital_Database.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Patient patient = new Patient("Pesho", "Peshev", "Nova", "pesho@abv.bg", Date.valueOf("2032-2-3"), true);
        em.persist(patient);

        Diagnose diagnose = new Diagnose("Losho", "Sha sa oprai", patient);
        em.persist(diagnose);

        Visitation visitation = new Visitation(Date.valueOf("2043-2-1"), "dobre e", patient);
        em.persist(visitation);

        Medicament medicament = new Medicament("Aspirin", patient);
        em.persist(medicament);

        em.getTransaction().commit();
    }
}
