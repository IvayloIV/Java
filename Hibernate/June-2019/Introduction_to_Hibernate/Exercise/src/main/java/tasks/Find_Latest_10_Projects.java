package tasks;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Find_Latest_10_Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Project> latestProjects = em.createNativeQuery("SELECT * FROM (SELECT * FROM projects\n" +
                "ORDER BY start_date DESC\n" +
                "LIMIT 10) p\n" +
                "ORDER BY p.name;", Project.class)
            .getResultList();

        latestProjects.forEach(p -> System.out.printf("Project name: %s\n" +
                " \tProject Description: %s\n" +
                " \tProject Start Date: %s\n" +
                " \tProject End Date: %s\n",
            p.getName(),
            p.getDescription(),
            p.getStartDate(),
            p.getEndDate()));

        em.getTransaction().commit();
    }
}
