package judge.domain.models.services;

import java.util.Date;
import java.util.List;

public class SubmissionServiceModel {

    private String id;

    private List<String> code;

    private Integer achievedResult;

    private Date createdOn;

    private ProblemServiceModel problem;

    private UserServiceModel user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ProblemServiceModel getProblem() {
        return problem;
    }

    public void setProblem(ProblemServiceModel problem) {
        this.problem = problem;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
