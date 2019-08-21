package tasks;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Adding_a_New_Address_and_Updating_Employee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        String lastName = sc.nextLine();

        em.getTransaction().begin();

        Town town = em.createQuery("FROM Town WHERE name = 'Sofia'", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        em.persist(address);

        Employee employee = em.createQuery("FROM Employee WHERE last_name = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        em.detach(employee);
        employee.setAddress(address);
        em.merge(employee);

        em.getTransaction().commit();
    }
}
