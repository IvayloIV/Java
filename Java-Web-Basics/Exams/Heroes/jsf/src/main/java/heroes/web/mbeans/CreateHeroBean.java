package heroes.web.mbeans;

import heroes.domain.enums.HeroClass;
import heroes.domain.models.bindings.HeroBindingModel;
import heroes.domain.models.services.HeroServiceModel;
import heroes.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreateHeroBean {

    private HeroBindingModel heroBindingModel;
    private List<String> heroesClass;

    private ModelMapper modelMapper;
    private HeroService heroService;

    public CreateHeroBean() {
    }

    @Inject
    public CreateHeroBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    public void load() {
        this.heroBindingModel = new HeroBindingModel();
        this.heroesClass = Arrays.stream(HeroClass.values())
                .map(HeroClass::name)
                .collect(Collectors.toList());
    }

    public HeroBindingModel getHeroBindingModel() {
        return heroBindingModel;
    }

    public List<String> getHeroesClass() {
        return heroesClass;
    }

    public String create() {
        HeroServiceModel heroServiceModel = this.modelMapper
                .map(this.heroBindingModel, HeroServiceModel.class);

        if (!this.heroService.create(heroServiceModel)) {
            return "pretty:login";
        }

        return "pretty:home";
    }
}
