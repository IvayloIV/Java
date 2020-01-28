package torshia.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Random;

public class ApplicationBeanConfiguration {

    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("torshia")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public Random random() {
        return new Random();
    }
}
