package heroes.web.mbeans;


import heroes.domain.models.views.HeroDetailsViewModel;
import heroes.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsHeroBean {

    private HeroDetailsViewModel heroDetailsViewModel;

    private ModelMapper modelMapper;
    private HeroService heroService;

    public DetailsHeroBean() {
    }

    @Inject
    public DetailsHeroBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    public void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String heroId = context.getRequestParameterMap().get("heroId");
        this.heroDetailsViewModel = this.modelMapper
                .map(this.heroService.getById(heroId), HeroDetailsViewModel.class);
    }

    public HeroDetailsViewModel getHeroDetailsViewModel() {
        return this.heroDetailsViewModel;
    }
}
