package judge.domain.models.bindings;

public class CreateSubmissionBindingModel {

    private String code;

    private String problemName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }
}
