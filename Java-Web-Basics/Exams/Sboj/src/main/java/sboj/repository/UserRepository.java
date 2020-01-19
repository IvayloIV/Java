package sboj.repository;

import sboj.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getUsername(String username);
}
