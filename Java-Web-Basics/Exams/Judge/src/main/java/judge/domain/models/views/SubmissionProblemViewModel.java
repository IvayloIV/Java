package judge.domain.models.views;

public class SubmissionProblemViewModel {

    private String id;

    private String userUsername;

    private Integer achievedResult;

    private Integer percentagePoints;

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

    public Integer getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(Integer achievedResult) {
        this.achievedResult = achievedResult;
    }

    public Integer getPercentagePoints() {
        return percentagePoints;
    }

    public void setPercentagePoints(Integer percentagePoints) {
        this.percentagePoints = percentagePoints;
    }
}
