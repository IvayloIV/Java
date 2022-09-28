package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "legs")
public class Leg {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer height;

    @ManyToMany(mappedBy = "legs")
    private Set<Cat> cats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public Set<Cat> getCats() {
        return cats;
    }

    public void setCats(Set<Cat> cats) {
        this.cats = cats;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
