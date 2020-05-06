package heroes.web.mbeans;

import heroes.domain.models.views.HeroHomeViewModel;
import heroes.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllHeroBean {

    private List<HeroHomeViewModel> heroes;

    private ModelMapper modelMapper;
    private HeroService heroService;

    public AllHeroBean() {
    }

    @Inject
    public AllHeroBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    public void load() {
        this.heroes = this.heroService.getAllByLevelDesc()
                .stream()
                .map(h -> this.modelMapper.map(h, HeroHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<HeroHomeViewModel> getHeroes() {
        return this.heroes;
    }
}
