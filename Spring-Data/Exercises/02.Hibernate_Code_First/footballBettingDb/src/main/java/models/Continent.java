package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "continents")
@Data
public class Continent extends Identity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;
}
