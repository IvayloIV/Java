package Bills_Payment_System;

import Bills_Payment_System.entities.BankAccount;
import Bills_Payment_System.entities.CreditCard;
import Bills_Payment_System.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bills_payment_system");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

//        User user1 = new User("Pesho1", "Peshov", "pesho@abv.bg", "1234");
//        User user2 = new User("Pesho2", "Peshov", "pesho@abv.bg", "1234");
//
//        BankAccount bankAccount = new BankAccount("4376545", user1, "Fib", "weq");
//        CreditCard creditCard = new CreditCard("2123", user2, "val", 4, 2023);
//
//        em.persist(bankAccount);
//        em.persist(creditCard);

        User user = em.createQuery("FROM User WHERE id = 2", User.class)
                .getSingleResult();

        System.out.println(user.getBill().getNumber());

        em.getTransaction().commit();
    }
}
