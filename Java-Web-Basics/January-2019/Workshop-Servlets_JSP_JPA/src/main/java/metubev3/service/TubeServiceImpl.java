package metubev3.service;

import metubev3.domain.entities.Tube;
import metubev3.domain.enums.TubeStatus;
import metubev3.domain.models.services.TubeServiceModel;
import metubev3.repository.TubeRepository;
import metubev3.repository.UserRepository;
import metubev3.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(TubeServiceModel tubeServiceModel, String uploader) {
        tubeServiceModel.setUploader(this.userRepository.findByUsername(uploader));
        return this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public List<TubeServiceModel> getAll() {
        return this.tubeRepository.findAll()
                .stream()
                .map(t -> this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TubeServiceModel> getByUsername(String username) {
        return this.tubeRepository.findByUsername(username)
                .stream()
                .map(t -> this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void increaseViews(String tubeId) {
        this.tubeRepository.increaseViews(tubeId);
    }

    @Override
    public TubeServiceModel getById(String tubeId) {
        return this.modelMapper.map(this.tubeRepository.findById(tubeId), TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> getAllWithStatus(TubeStatus status) {
        return this.tubeRepository.findAllWithStatus(status)
                .stream()
                .map(t -> this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatus(String tubeId, TubeStatus status) {
        this.tubeRepository.changeStatus(tubeId, status);
    }
}
