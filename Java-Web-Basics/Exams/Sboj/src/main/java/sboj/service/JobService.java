package sboj.service;

import sboj.domain.models.services.JobApplicationServiceModel;

import java.util.List;

public interface JobService {

    public boolean save(JobApplicationServiceModel job);

    public List<JobApplicationServiceModel> getAllJob();

    public JobApplicationServiceModel getById(String id);

    public boolean removeById(String jobId);
}
