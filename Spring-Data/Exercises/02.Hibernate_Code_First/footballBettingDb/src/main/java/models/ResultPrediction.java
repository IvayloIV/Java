package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "result_predictions")
@Data
public class ResultPrediction extends Identity {

    @Column(nullable = false)
    private String prediction;

    @OneToMany(mappedBy = "resultPrediction")
    private Set<BetGame> betGames;
}
