package torshia.repository;

import torshia.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getUsername(String username);
}
