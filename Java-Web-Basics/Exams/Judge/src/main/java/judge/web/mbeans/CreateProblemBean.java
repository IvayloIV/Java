package judge.web.mbeans;

import judge.domain.models.bindings.CreateProblemBindingModel;
import judge.domain.models.services.ProblemServiceModel;
import judge.service.ProblemService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateProblemBean {

    private CreateProblemBindingModel createProblemBM;

    private ModelMapper modelMapper;
    private ProblemService problemService;

    public CreateProblemBean() {
    }

    @Inject
    public CreateProblemBean(ModelMapper modelMapper, ProblemService problemService) {
        this.modelMapper = modelMapper;
        this.problemService = problemService;
    }

    @PostConstruct
    public void init() {
        this.createProblemBM = new CreateProblemBindingModel();
    }

    public String createProblem() {
        ProblemServiceModel problemServiceModel = this.modelMapper
                .map(this.createProblemBM, ProblemServiceModel.class);

        if (!this.problemService.create(problemServiceModel)) {
            return "pretty:createProblem";
        }
        return "pretty:home";
    }

    public CreateProblemBindingModel getCreateProblemBM() {
        return createProblemBM;
    }
}
