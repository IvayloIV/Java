package torshia.domain.entities;

import torshia.domain.enums.ReportStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status;

    @Column(name = "reported_on")
    private Date reportedOn;

    @OneToOne(targetEntity = Task.class)
    @JoinColumn(name = "task", referencedColumnName = "id")
    private Task task;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "reporter", referencedColumnName = "id")
    private User reporter;

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Date getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(Date reportedOn) {
        this.reportedOn = reportedOn;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }
}
