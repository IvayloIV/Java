package judge.service;

import judge.domain.entities.Problem;
import judge.domain.models.services.ProblemServiceModel;
import judge.repository.ProblemRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {

    private ModelMapper modelMapper;
    private ProblemRepository problemRepository;

    @Inject
    public ProblemServiceImpl(ModelMapper modelMapper, ProblemRepository problemRepository) {
        this.modelMapper = modelMapper;
        this.problemRepository = problemRepository;
    }

    @Override
    public boolean create(ProblemServiceModel problemServiceModel) {
        return this.problemRepository
                .save(this.modelMapper.map(problemServiceModel, Problem.class)) != null;
    }

    @Override
    public List<ProblemServiceModel> getAllProblems() {
        return this.problemRepository.getAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProblemServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProblemServiceModel getById(String problemId) {
        return this.modelMapper
                .map(this.problemRepository.getById(problemId), ProblemServiceModel.class);
    }
}
