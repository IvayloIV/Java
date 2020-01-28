package torshia.domain.models.views;

import java.util.Date;

public class TaskDetailViewModel {

    private String title;

    private Date dueDate;

    private String participants;

    private String affectedSectors;

    private String description;

    private Long level;

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

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getAffectedSectors() {
        return affectedSectors;
    }

    public void setAffectedSectors(String affectedSectors) {
        this.affectedSectors = affectedSectors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
