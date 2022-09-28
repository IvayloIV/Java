package spring.demo.shampoos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.shampoos.models.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
