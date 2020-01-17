package panda.repository;

import panda.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    public User getByUsername(String username);
}
