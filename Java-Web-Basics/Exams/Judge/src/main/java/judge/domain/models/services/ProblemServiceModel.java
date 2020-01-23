package judge.domain.models.services;

import java.util.List;

public class ProblemServiceModel {

    private String id;

    private String name;

    private Integer points;

    private List<SubmissionServiceModel> submissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<SubmissionServiceModel> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionServiceModel> submissions) {
        this.submissions = submissions;
    }
}
