package models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bet_games")
@Data
public class BetGame {

    @EmbeddedId
    private BetGameId id;

    @ManyToOne
    @JoinColumn(name = "result_prediction", referencedColumnName = "id")
    private ResultPrediction resultPrediction;
}
