package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competitions")
@Data
public class Competition extends Identity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type_id", referencedColumnName = "id")
    private CompetitionType competitionType;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;
}
