package models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
public class Team extends Identity {

    @Column(nullable = false, unique = true, length = 512)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false, unique = true, length = 3)
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color", referencedColumnName = "id")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color", referencedColumnName = "id")
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @Column(nullable = false)
    private BigDecimal budget;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Game> homeTeams;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Game> awayTeams;
}
