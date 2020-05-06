package heroes.repository;

import heroes.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getUsername(String username);
}
