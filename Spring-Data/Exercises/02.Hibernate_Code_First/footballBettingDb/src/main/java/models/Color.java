package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "colors")
@Data
public class Color extends Identity {

    @Column(nullable = false, unique = true, length = 127)
    private String name;

    @OneToMany(mappedBy = "primaryKitColor")
    private Set<Team> primaryKitColors;

    @OneToMany(mappedBy = "secondaryKitColor")
    private Set<Team> secondaryKitColors;
}
