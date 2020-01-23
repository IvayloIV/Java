package judge.domain.models.views;

import java.util.List;

public class ProblemDetailsViewModel {

    private String id;

    private String name;

    private Integer points;

    private List<SubmissionProblemViewModel> submissions;

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

    public List<SubmissionProblemViewModel> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionProblemViewModel> submissions) {
        this.submissions = submissions;
    }
}
