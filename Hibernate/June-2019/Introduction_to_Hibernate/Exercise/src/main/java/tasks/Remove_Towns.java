package tasks;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Remove_Towns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String townName = sc.nextLine();
        em.getTransaction().begin();
        Town town = em.createQuery("FROM Town WHERE name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        List<Address> addresses = em.createQuery("FROM Address WHERE town_id = :townId", Address.class)
                .setParameter("townId", town.getId())
                .getResultList();

        int countOfAddresses = addresses.size();

        addresses.forEach(em::remove);
        em.remove(town);
        System.out.println(countOfAddresses + " address in Sofia deleted");
        em.getTransaction().commit();
    }
}
