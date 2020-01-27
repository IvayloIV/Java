package torshia.domain.entities;

import torshia.domain.enums.TaskSector;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "is_reported", nullable = false)
    private Boolean isReported;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @Column(name = "participants")
    private List<String> participants;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "affected_sectors")
    private List<TaskSector> affectedSectors;

    @OneToOne(targetEntity = Report.class, mappedBy = "task")
    private Report report;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<TaskSector> getAffectedSectors() {
        return affectedSectors;
    }

    public void setAffectedSectors(List<TaskSector> affectedSectors) {
        this.affectedSectors = affectedSectors;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
