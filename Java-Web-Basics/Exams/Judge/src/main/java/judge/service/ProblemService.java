package judge.service;

import judge.domain.models.services.ProblemServiceModel;

import java.util.List;

public interface ProblemService {

    public boolean create(ProblemServiceModel problemServiceModel);

    public List<ProblemServiceModel> getAllProblems();

    public ProblemServiceModel getById(String problemId);
}
