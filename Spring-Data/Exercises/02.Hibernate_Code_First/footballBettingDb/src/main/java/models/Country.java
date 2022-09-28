package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
@Data
public class Country {

    @Id
    @Column(length = 3)
    private String id;

    @Column(nullable = false, unique = true, length = 127)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "countries_continents",
        joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id")
    )
    private Set<Continent> continents;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
