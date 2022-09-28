package models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
public class Game extends Identity {

    @ManyToOne
    @JoinColumn(name = "home_team", referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team", referencedColumnName = "id")
    private Team awayTeam;

    @Column(name = "home_goals", nullable = false)
    private Integer homeGoals;

    @Column(name = "away_goals", nullable = false)
    private Integer awayGoals;

    @Column(name = "played_time")
    private LocalDateTime playedTime;

    @Column(name = "home_team_win_bet_rate")
    private Double homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private Double awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private Double drawGameBetRate;

    @OneToMany(mappedBy = "id.game")
    private Set<PlayerStatistic> playerStatistics;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @OneToMany(mappedBy = "id.game")
    private Set<BetGame> betGames;
}
