package spring.demo.accountsystem.dao;

import org.springframework.data.repository.CrudRepository;
import spring.demo.accountsystem.models.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
