package spring.demo.shampoos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "labels")
@Data
@EqualsAndHashCode(callSuper = true)
public class Label extends BaseEntity {

    private String title;

    private String subtitle;

    @OneToMany(mappedBy = "label")
    private Set<Shampoo> shampoos;
}
