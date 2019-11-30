package metubev3.domain.entities;

import metubev3.domain.enums.TubeStatus;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "youtube_id", nullable = false)
    private String youtubeId;

    @Column(name = "views")
    @ColumnDefault(value = "0")
    private int views;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader_id", referencedColumnName = "id")
    private User uploader;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }
}
