package metube.service;

import metube.domain.entity.Tube;
import metube.domain.model.service.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.util.ModelMapper;
import metube.util.ValidationUtilImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void save(TubeServiceModel tubeServiceModel) {
        if (!this.validationUtil.isValid(tubeServiceModel)) {
            throw new IllegalArgumentException();
        }
        Tube tybe = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tybe);
    }

    @Override
    public List<TubeServiceModel> findAll() {
        return this.tubeRepository.findAll().stream()
                .map(t -> this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TubeServiceModel findByName(String name) {
        return this.modelMapper
                .map(this.tubeRepository.findByName(name), TubeServiceModel.class);
    }
}
