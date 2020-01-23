package judge.web.mbeans;

import judge.domain.models.services.ProblemServiceModel;
import judge.domain.models.services.SubmissionServiceModel;
import judge.domain.models.views.ProblemHomeViewModel;
import judge.service.ProblemService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class HomeProblemBean {

    private List<ProblemHomeViewModel> problemHomeVM;

    private ModelMapper modelMapper;
    private ProblemService problemService;

    public HomeProblemBean() {
    }

    @Inject
    public HomeProblemBean(ModelMapper modelMapper, ProblemService problemService) {
        this.modelMapper = modelMapper;
        this.problemService = problemService;
    }

    @PostConstruct
    public void init() {
        this.problemHomeVM = new ArrayList<>();
        List<ProblemServiceModel> problemServiceModels = this.problemService.getAllProblems();
        for (ProblemServiceModel problemServiceModel : problemServiceModels) {
            ProblemHomeViewModel problemHomeViewModel = this.modelMapper
                    .map(problemServiceModel, ProblemHomeViewModel.class);

            Integer maxPointsSubmission = getMaxSubmissionPoints(problemServiceModel);
            Integer completed = (int) Math.round(maxPointsSubmission / (double)problemServiceModel.getPoints() * 100);
            problemHomeViewModel.setCompleted(completed);
            this.problemHomeVM.add(problemHomeViewModel);
        }
    }

    private Integer getMaxSubmissionPoints(ProblemServiceModel problemServiceModel) {
        Integer maxPointsSubmission = 0;

        if (problemServiceModel.getSubmissions().size() > 0) {
            maxPointsSubmission = problemServiceModel.getSubmissions()
                    .stream()
                    .map(SubmissionServiceModel::getAchievedResult)
                    .max(Integer::compareTo)
                    .get();
        }
        return maxPointsSubmission;
    }

    public List<ProblemHomeViewModel> getProblemHomeVM() {
        return problemHomeVM;
    }
}
