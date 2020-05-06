package heroes.service;

import heroes.domain.entities.Hero;
import heroes.domain.models.services.HeroServiceModel;
import heroes.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {

    private final ModelMapper modelMapper;
    private final HeroRepository heroRepository;

    @Inject
    public HeroServiceImpl(ModelMapper modelMapper, HeroRepository heroRepository) {
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
    }

    @Override
    public boolean create(HeroServiceModel heroServiceModel) {
        Hero hero = this.modelMapper.map(heroServiceModel, Hero.class);
        return this.heroRepository.save(hero);
    }

    @Override
    public List<HeroServiceModel> getAll() {
        return this.getAllHeroes(this.heroRepository::getAll);
    }

    @Override
    public List<HeroServiceModel> getAllByLevelDesc() {
        return this.getAllHeroes(this.heroRepository::getAllByLevelDesc);
    }

    private List<HeroServiceModel> getAllHeroes(Supplier<List<Hero>> heroes) {
        return heroes.get()
                .stream()
                .map(a -> this.modelMapper.map(a, HeroServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HeroServiceModel getById(String id) {
        Hero hero = this.heroRepository.getById(id);
        if (hero == null) {
            return null;
        }
        return this.modelMapper.map(hero, HeroServiceModel.class);
    }

    @Override
    public boolean removeById(String id) {
        return this.heroRepository.removeById(id);
    }
}
