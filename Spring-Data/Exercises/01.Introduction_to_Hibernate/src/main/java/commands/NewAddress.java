package commands;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewAddress implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Address address = new Address();
            address.setText("Vitoshka 15");

            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();

            Query query = entityManager
                    .createQuery("UPDATE Employee e " +
                            "SET e.address = :address " +
                            "WHERE e.lastName = :lastName");

            query.setParameter("address", address);
            query.setParameter("lastName", br.readLine());

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
