package Hospital_Database.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visitations")
public class Visitation extends Identity {
    @Column
    private Date date;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Visitation() {
    }

    public Visitation(Date date, String comment, Patient patient) {
        this.date = date;
        this.comment = comment;
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
