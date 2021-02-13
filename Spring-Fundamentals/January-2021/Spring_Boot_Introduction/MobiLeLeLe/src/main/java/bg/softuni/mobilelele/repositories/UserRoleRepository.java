package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
