package app.ccb.domain.dtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class EmployeeDto {
    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    private BigDecimal salary;

    @Expose
    @SerializedName("started_on")
    private Date startedOn;

    @Expose
    @SerializedName("branch_name")
    private String branchName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
