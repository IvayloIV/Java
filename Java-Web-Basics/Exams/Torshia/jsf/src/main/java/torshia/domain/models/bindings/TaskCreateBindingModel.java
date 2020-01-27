package torshia.domain.models.bindings;

import java.util.Date;
import java.util.List;

public class TaskCreateBindingModel {

    private String title;

    private Date dueDate;

    private Boolean isReported;

    private String description;

    private String participants;

    private List<String> affectedSectors;

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

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public List<String> getAffectedSectors() {
        return affectedSectors;
    }

    public void setAffectedSectors(List<String> affectedSectors) {
        this.affectedSectors = affectedSectors;
    }
}
