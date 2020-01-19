package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entities.JobApplication;
import sboj.domain.models.services.JobApplicationServiceModel;
import sboj.repository.JobRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {

    private ModelMapper modelMapper;
    private JobRepository jobRepository;

    public JobServiceImpl() {
    }

    @Inject
    public JobServiceImpl(ModelMapper modelMapper, JobRepository jobRepository) {
        this.modelMapper = modelMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean save(JobApplicationServiceModel job) {
        return this.jobRepository
                .save(this.modelMapper.map(job, JobApplication.class));
    }

    @Override
    public List<JobApplicationServiceModel> getAllJob() {
        return this.jobRepository.getAll()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        return this.modelMapper
                .map(this.jobRepository.getById(id), JobApplicationServiceModel.class);
    }

    @Override
    public boolean removeById(String jobId) {
        return this.jobRepository.removeById(jobId);
    }
}
