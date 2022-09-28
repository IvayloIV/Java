package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "player_statistics")
@Data
public class PlayerStatistic {

    @EmbeddedId
    private PlayerStatisticId id;

    @Column(name = "scored_goals", nullable = false)
    private Integer scoredGoals;

    @Column(name = "player_assists", nullable = false)
    private Integer playerAssists;

    @Column(name = "played_minutes")
    private Integer playedMinutes;
}
