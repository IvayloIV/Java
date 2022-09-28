package models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
@Data
public class Visitation extends Identity {

    @Column(nullable = false)
    private LocalDate date;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
