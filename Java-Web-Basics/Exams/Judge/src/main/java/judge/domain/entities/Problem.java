package judge.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "problem")
public class Problem extends BaseEntity {

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "points", nullable = false)
    private Integer points;

    @OneToMany(targetEntity = Submission.class, mappedBy = "problem")
    private List<Submission> submissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
