package fdmc.service;

import fdmc.domain.entity.Cat;
import fdmc.domain.model.service.CatServiceModel;
import fdmc.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private CatRepository catRepository;
    private ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(CatServiceModel catServiceModel) {
        if (validateFields(catServiceModel)) {
            return false;
        }

        try {
            this.catRepository.save(this.modelMapper.map(catServiceModel, Cat.class));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<CatServiceModel> getAll() {
        return this.catRepository.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    private boolean validateFields(CatServiceModel catServiceModel) {
        if (catServiceModel.getName().length() < 2 ||
                catServiceModel.getName().length() > 10) {
            return true;
        }

        if (catServiceModel.getBreed().length() < 5 ||
                catServiceModel.getBreed().length() > 20) {
            return true;
        }

        if (catServiceModel.getAge() < 1 || catServiceModel.getAge() > 31) {
            return true;
        }

        return catServiceModel.getPrice() < 0.01;
    }
}
