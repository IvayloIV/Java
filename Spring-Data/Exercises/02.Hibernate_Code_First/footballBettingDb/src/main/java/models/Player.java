package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
@Data
public class Player extends Identity {

    @Column(nullable = false, unique = true, length = 127)
    private String name;

    @Column(name = "squad_number")
    private Integer squadNumber;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @Column(name = "is_currently_injured")
    private Boolean isCurrentlyInjured;

    @OneToMany(mappedBy = "id.player")
    private Set<PlayerStatistic> playerStatistics;
}
