package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.views.JobRemoveModelView;
import sboj.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RemoveJobBean {

   private JobRemoveModelView job;

   private ModelMapper modelMapper;
   private JobService jobService;

    public RemoveJobBean() {
    }

    @Inject
    public RemoveJobBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void init() {
        this.job = this.modelMapper
                .map(this.jobService.getById(this.getJobId()), JobRemoveModelView.class);
    }

    public String removeJob() {
        if (!this.jobService.removeById(this.job.getId())) {
            return "pretty:removeJob?jobId=" + this.getJobId();
        }
        return "pretty:home";
    }

    private String getJobId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return context.getRequestParameterMap().get("jobId");
    }

    public JobRemoveModelView getJob() {
        return job;
    }
}
