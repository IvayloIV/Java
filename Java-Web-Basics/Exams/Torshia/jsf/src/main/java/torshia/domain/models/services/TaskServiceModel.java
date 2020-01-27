package torshia.domain.models.services;

import torshia.domain.enums.TaskSector;

import java.util.Date;
import java.util.List;

public class TaskServiceModel {

    private String id;

    private String title;

    private Date dueDate;

    private Boolean isReported;

    private String description;

    private List<String> participants;

    private List<TaskSector> affectedSectors;

    private ReportServiceModel report;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public ReportServiceModel getReport() {
        return report;
    }

    public void setReport(ReportServiceModel report) {
        this.report = report;
    }
}
