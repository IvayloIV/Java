package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.enums.Sector;
import sboj.domain.models.bindings.JobApplicationBindingModel;
import sboj.domain.models.services.JobApplicationServiceModel;
import sboj.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateJobBean {

    private JobApplicationBindingModel job;

    private ModelMapper modelMapper;
    private JobService jobService;

    public CreateJobBean() {
    }

    @Inject
    public CreateJobBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void load() {
        this.job = new JobApplicationBindingModel();
    }

    public JobApplicationBindingModel getJob() {
        return job;
    }

    public String createJob() {
        if (!Sector.containsSector(this.job.getSector())) {
            return "pretty:createJob";
        }

        JobApplicationServiceModel jobApplicationServiceModel = this.modelMapper
                .map(this.job, JobApplicationServiceModel.class);

        if (!this.jobService.save(jobApplicationServiceModel)) {
            return "pretty:createJob";
        }

        return "pretty:home";
    }
}
