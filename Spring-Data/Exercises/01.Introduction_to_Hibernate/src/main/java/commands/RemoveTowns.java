package commands;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.*;

public class RemoveTowns implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String townName = br.readLine();

            TypedQuery<Town> query = entityManager
                    .createQuery("SELECT t FROM Town t " +
                            "WHERE t.name = :townName", Town.class);

            query.setParameter("townName", townName);
            Town town = query.getSingleResult();
            Integer addresses = town.getAddresses().size();

            entityManager.getTransaction().begin();
            entityManager.remove(town);
            entityManager.getTransaction().commit();

            bw.write(String.format("%d address in %s deleted", addresses, town.getName()));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
