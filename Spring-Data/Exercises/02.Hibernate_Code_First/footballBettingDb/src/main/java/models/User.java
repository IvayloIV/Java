package models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User extends Identity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 62)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false, length = 127)
    private String fullName;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "user")
    private Set<Bet> bets;
}
