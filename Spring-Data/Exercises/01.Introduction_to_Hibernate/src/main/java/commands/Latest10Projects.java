package commands;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Comparator;

public class Latest10Projects implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<Project> query = entityManager
                .createQuery("SELECT p FROM Project p " +
                        "ORDER BY p.startDate desc", Project.class);

        query.setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("Project name: %s\n", p.getName());
                    System.out.printf(" Project Description: %s\n", p.getDescription());
                    System.out.printf(" Project Start Date: %s\n", p.getStartDate());
                    System.out.printf(" Project End Date: %s\n", p.getEndDate());
                });
    }
}
