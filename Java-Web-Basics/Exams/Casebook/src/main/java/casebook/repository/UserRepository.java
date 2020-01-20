package casebook.repository;

import casebook.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getUsername(String username);

    public boolean updateUser(User user);
}
