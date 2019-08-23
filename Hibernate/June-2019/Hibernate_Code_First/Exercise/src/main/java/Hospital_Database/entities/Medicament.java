package Hospital_Database.entities;

import javax.persistence.*;

@Entity
@Table(name = "medicaments")
public class Medicament extends Identity {
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Medicament(String name, Patient patient) {
        this.name = name;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
