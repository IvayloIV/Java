package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "medicaments")
@Data
public class Medicament extends Identity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Patient> patients;
}
