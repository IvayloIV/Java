package metubev3.repository;

import metubev3.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    public User login(User user);

    public User findByUsername(String username);

    public Long size();
}
