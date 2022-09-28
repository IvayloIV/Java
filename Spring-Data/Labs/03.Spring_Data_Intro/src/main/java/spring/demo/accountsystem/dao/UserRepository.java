package spring.demo.accountsystem.dao;

import org.springframework.data.repository.CrudRepository;
import spring.demo.accountsystem.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public boolean existsByUsername(String username);
}
