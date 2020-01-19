package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.views.JobDetailsViewModel;
import sboj.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobDetailsBean {

    private JobDetailsViewModel jobDetails;

    private ModelMapper modelMapper;
    private JobService jobService;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("jobId");
        this.jobDetails = this.modelMapper
                .map(this.jobService.getById(id), JobDetailsViewModel.class);
    }

    public JobDetailsViewModel getJobDetails() {
        return jobDetails;
    }
}
