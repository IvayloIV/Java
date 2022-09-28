package models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bets")
@Data
public class Bet extends Identity {

    @Column(name = "bet_money", nullable = false)
    private BigDecimal betMoney;

    @Column(name = "bet_time")
    private LocalDateTime betTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "id.bet")
    private Set<BetGame> betGames;
}
