package spring.demo.shampoos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import spring.demo.shampoos.models.BaseEntity;

@NoRepositoryBean
public interface MainRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    public T findOneById(Long id);
}
