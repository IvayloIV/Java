import models.ingredients.AmmoniumChloride;
import models.ingredients.BasicIngredient;
import models.ingredients.Mint;
import models.ingredients.Nettle;
import models.labels.BasicLabel;
import models.shampoos.BasicShampoo;
import models.shampoos.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mind and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);
        shampoo.getIngredients().add(am);
        em.persist(shampoo);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
