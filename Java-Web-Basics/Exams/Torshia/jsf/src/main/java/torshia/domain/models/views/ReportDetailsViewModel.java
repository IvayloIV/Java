package torshia.domain.models.views;

import torshia.domain.enums.ReportStatus;

import java.util.Date;

public class ReportDetailsViewModel {

    private String id;

    private ReportStatus status;

    private Date reportedOn;

    private String reporterUsername;

    private TaskDetailViewModel task;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getReporterUsername() {
        return reporterUsername;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public TaskDetailViewModel getTask() {
        return task;
    }

    public void setTask(TaskDetailViewModel task) {
        this.task = task;
    }
}
