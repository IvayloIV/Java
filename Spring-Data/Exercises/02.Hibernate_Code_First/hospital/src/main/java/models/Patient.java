package models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
@Data
public class Patient extends Identity {

    @Column(name = "first_name", length = 80)
    private String firstName;

    @Column(name = "last_name", length = 80, nullable = false)
    private String lastName;

    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birthDate;

    private String picture;

    @Column(name = "medical_insurance", nullable = false)
    private Boolean medicalInsurance;

    @OneToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    @ManyToMany
    @JoinTable(
        name = "patient_medicaments",
        joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    )
    private Set<Medicament> medicaments;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;
}
