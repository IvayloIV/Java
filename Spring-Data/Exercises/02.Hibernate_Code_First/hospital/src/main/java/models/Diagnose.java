package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "diagnoses")
@Data
public class Diagnose extends Identity {

    @Column(nullable = false)
    private String name;

    private String comments;

    @OneToOne(mappedBy = "diagnose")
    private Patient patient;
}
