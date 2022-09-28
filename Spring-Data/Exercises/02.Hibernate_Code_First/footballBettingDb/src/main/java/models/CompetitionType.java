package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "competition_types")
@Data
public class CompetitionType extends Identity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "competitionType")
    private Set<Competition> competitions;
}
