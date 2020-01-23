package judge.web.mbeans;

import judge.domain.models.bindings.CreateSubmissionBindingModel;
import judge.domain.models.services.ProblemServiceModel;
import judge.domain.models.services.SubmissionServiceModel;
import judge.domain.models.services.UserServiceModel;
import judge.service.ProblemService;
import judge.service.SubmissionService;
import judge.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreateSubmissionBean {

    private CreateSubmissionBindingModel createSubmissionBM;

    private ModelMapper modelMapper;
    private SubmissionService submissionService;
    private ProblemService problemService;
    private UserService userService;

    public CreateSubmissionBean() {
    }

    @Inject
    public CreateSubmissionBean(ModelMapper modelMapper,
                                SubmissionService submissionService,
                                ProblemService problemService,
                                UserService userService) {
        this.modelMapper = modelMapper;
        this.submissionService = submissionService;
        this.problemService = problemService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String problemId = context.getRequestParameterMap().get("problemId");
        this.createSubmissionBM = new CreateSubmissionBindingModel();
        this.createSubmissionBM
                .setProblemName(this.problemService.getById(problemId).getName());
    }

    public void createSubmission() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String problemId = context.getRequestParameterMap().get("problemId");
        ProblemServiceModel problem = this.problemService.getById(problemId);

        if (problem == null) {
            context.redirect("/faces/view/home.xhtml");
            return;
        }
        String userId = (String) context.getSessionMap().get("id");
        UserServiceModel user = this.userService.getById(userId);
        List<String> codes = Arrays.stream(this.createSubmissionBM.getCode()
                                    .split("\r\n"))
                                    .filter(s -> s.trim().length() != 0)
                                    .collect(Collectors.toList());

        SubmissionServiceModel submission = createSubmission(problem, user, codes);
        SubmissionServiceModel createdSubmission = this.submissionService.createSubmission(submission);
        if (createdSubmission == null) {
            context.redirect("/faces/view/createSubmission.xhtml?problemId=" + problemId);
            return;
        }
        context.redirect("/faces/view/submissionDetails.xhtml?submissionId=" + createdSubmission.getId());
    }

    private SubmissionServiceModel createSubmission(ProblemServiceModel problem, UserServiceModel user, List<String> codes) {
        SubmissionServiceModel submissionServiceModel = new SubmissionServiceModel();
        submissionServiceModel.setProblem(problem);
        submissionServiceModel.setUser(user);
        submissionServiceModel.setCode(codes);
        return submissionServiceModel;
    }

    public CreateSubmissionBindingModel getCreateSubmissionBM() {
        return createSubmissionBM;
    }
}
