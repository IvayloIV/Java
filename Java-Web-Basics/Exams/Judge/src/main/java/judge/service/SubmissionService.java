package judge.service;

import judge.domain.models.services.SubmissionServiceModel;

public interface SubmissionService {

    public SubmissionServiceModel createSubmission(SubmissionServiceModel submissionServiceModel);

    public SubmissionServiceModel getById(String id);
}
