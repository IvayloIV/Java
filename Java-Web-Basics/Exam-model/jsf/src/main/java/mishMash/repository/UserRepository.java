package mishMash.repository;

import mishMash.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getUsername(String username);
}
