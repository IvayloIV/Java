package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.views.JobHomeViewModel;
import sboj.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobListBean {

    private List<JobHomeViewModel> jobsVM;

    private ModelMapper modelMapper;
    private JobService jobService;

    public JobListBean() {
    }

    @Inject
    public JobListBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void init() {
        this.jobsVM = this.jobService.getAllJob()
                .stream()
                .map(j -> this.modelMapper.map(j, JobHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobHomeViewModel> getJobsVM() {
        return jobsVM;
    }
}
