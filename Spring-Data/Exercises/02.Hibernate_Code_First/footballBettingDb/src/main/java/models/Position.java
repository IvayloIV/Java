package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positions")
@Data
public class Position {

    @Id
    @Column(length = 2)
    private String id;

    @Column(name = "position_description", length = 1023)
    private String positionDescription;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;
}
