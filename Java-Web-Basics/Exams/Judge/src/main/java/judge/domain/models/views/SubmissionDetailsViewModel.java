package judge.domain.models.views;

import java.util.Date;
import java.util.List;

public class SubmissionDetailsViewModel {

    private String id;

    private String userUsername;

    private List<String> code;

    private Integer achievedResult;

    private Date createdOn;

    private SubmissionDetailsProbViewModel problem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public List<String> getCode() {
        return code;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public Integer getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(Integer achievedResult) {
        this.achievedResult = achievedResult;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public SubmissionDetailsProbViewModel getProblem() {
        return problem;
    }

    public void setProblem(SubmissionDetailsProbViewModel problem) {
        this.problem = problem;
    }
}
