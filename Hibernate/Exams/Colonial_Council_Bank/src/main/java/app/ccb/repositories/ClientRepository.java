package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByFullName(String fullName);

    @Query("SELECT c FROM Client c " +
            "ORDER BY SIZE(c.bankAccount.cards) DESC ")
    List<Client> getClientWithTheMostCards();
}
