package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
@Data
public class Town extends Identity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "town")
    private Set<Team> teams;
}
