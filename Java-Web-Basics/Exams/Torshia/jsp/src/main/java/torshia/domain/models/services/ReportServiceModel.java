package torshia.domain.models.services;

import torshia.domain.enums.ReportStatus;

import java.util.Date;

public class ReportServiceModel {

    private String id;

    private ReportStatus status;

    private Date reportedOn;

    private TaskServiceModel task;

    private UserServiceModel reporter;

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

    public TaskServiceModel getTask() {
        return task;
    }

    public void setTask(TaskServiceModel task) {
        this.task = task;
    }

    public UserServiceModel getReporter() {
        return reporter;
    }

    public void setReporter(UserServiceModel reporter) {
        this.reporter = reporter;
    }
}
