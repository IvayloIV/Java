package judge.web.mbeans;

import judge.domain.models.views.SubmissionDetailsViewModel;
import judge.service.SubmissionService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SubmissionDetailsBean {

    private SubmissionDetailsViewModel submissionDetailsVM;

    private ModelMapper modelMapper;
    private SubmissionService submissionService;

    public SubmissionDetailsBean() {
    }

    @Inject
    public SubmissionDetailsBean(ModelMapper modelMapper, SubmissionService submissionService) {
        this.modelMapper = modelMapper;
        this.submissionService = submissionService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String submissionId = context.getRequestParameterMap().get("submissionId");
        if (submissionId != null) {
            this.submissionDetailsVM = this.modelMapper
                    .map(this.submissionService.getById(submissionId), SubmissionDetailsViewModel.class);
        }
    }

    public SubmissionDetailsViewModel getSubmissionDetailsVM() {
        return submissionDetailsVM;
    }
}
