package judge.web.mbeans;

import judge.domain.models.views.ProblemDetailsViewModel;
import judge.domain.models.views.SubmissionProblemViewModel;
import judge.service.ProblemService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProblemDetailsBean {

    private ProblemDetailsViewModel problemDetailsVM;
    private Double successRate;

    private ModelMapper modelMapper;
    private ProblemService problemService;

    public ProblemDetailsBean() {
    }

    @Inject
    public ProblemDetailsBean(ModelMapper modelMapper, ProblemService problemService) {
        this.modelMapper = modelMapper;
        this.problemService = problemService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String problemId = context.getRequestParameterMap().get("problemId");
        if (problemId == null) {
            return;
        }

        this.problemDetailsVM = this.modelMapper
                .map(this.problemService.getById(problemId), ProblemDetailsViewModel.class);

        Integer maxResults = setSubmission();
        if (maxResults > 0) {
            this.successRate = maxResults / (double) this.problemDetailsVM.getSubmissions().size() * 100;
        } else {
            this.successRate = 0d;
        }
    }

    private Integer setSubmission() {
        Integer maxResults = 0;

        for (SubmissionProblemViewModel submission : this.problemDetailsVM.getSubmissions()) {
            Integer percentagePoint = (int) Math.round(submission.getAchievedResult() / (double) this.problemDetailsVM.getPoints() * 100);
            if (percentagePoint == 100) {
                maxResults++;
            }
            submission.setPercentagePoints(percentagePoint);
        }
        return maxResults;
    }

    public ProblemDetailsViewModel getProblemDetailsVM() {
        return problemDetailsVM;
    }

    public Double getSuccessRate() {
        return successRate;
    }
}
