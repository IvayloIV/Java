package judge.service;

import judge.domain.entities.Submission;
import judge.domain.models.services.SubmissionServiceModel;
import judge.repository.SubmissionRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class SubmissionServiceImpl implements SubmissionService {

    private ModelMapper modelMapper;
    private SubmissionRepository submissionRepository;
    private Random random;

    @Inject
    public SubmissionServiceImpl(ModelMapper modelMapper, SubmissionRepository submissionRepository, Random random) {
        this.modelMapper = modelMapper;
        this.submissionRepository = submissionRepository;
        this.random = random;
    }

    @Override
    public SubmissionServiceModel createSubmission(SubmissionServiceModel submissionServiceModel) {
        String code = submissionServiceModel.getCode()
                .stream()
                .collect(Collectors.joining("\r\n"))
                .trim();

        if (submissionServiceModel.getProblem().getName().equals(code)) {
            submissionServiceModel
                    .setAchievedResult(submissionServiceModel.getProblem().getPoints());
        } else {
            Integer submissionPoints = this.random
                    .nextInt(submissionServiceModel.getProblem().getPoints());
            submissionServiceModel.setAchievedResult(submissionPoints);
        }
        submissionServiceModel.setCreatedOn(new Date());

        Submission submission = this.submissionRepository
                .save(this.modelMapper.map(submissionServiceModel, Submission.class));

        return this.modelMapper.map(submission, SubmissionServiceModel.class);
    }

    @Override
    public SubmissionServiceModel getById(String id) {
        return this.modelMapper
                .map(this.submissionRepository.getById(id), SubmissionServiceModel.class);
    }
}
