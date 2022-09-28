package spring.demo.usersystem.dao;

import org.springframework.data.repository.CrudRepository;
import spring.demo.usersystem.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAllByEmailEndsWith(String email);

    public List<User> findAllByLastTimeLoggedInAfter(LocalDateTime localDateTime);

    public void deleteAllByIsDeletedTrue();
}
