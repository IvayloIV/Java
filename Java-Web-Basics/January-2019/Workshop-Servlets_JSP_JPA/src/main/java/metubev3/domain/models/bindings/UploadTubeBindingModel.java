package metubev3.domain.models.bindings;

import metubev3.domain.enums.TubeStatus;

public class UploadTubeBindingModel {
    private String title;
    private String author;
    private String youtubeId;
    private String description;
    private TubeStatus status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }
}
