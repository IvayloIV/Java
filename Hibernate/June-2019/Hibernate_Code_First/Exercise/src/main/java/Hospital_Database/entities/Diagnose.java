package Hospital_Database.entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends Identity {
    @Column
    private String name;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Diagnose(String name, String comment, Patient patient) {
        this.name = name;
        this.comment = comment;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
