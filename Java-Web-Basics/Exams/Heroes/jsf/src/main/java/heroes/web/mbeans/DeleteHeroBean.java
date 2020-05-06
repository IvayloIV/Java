package heroes.web.mbeans;

import heroes.domain.models.services.HeroServiceModel;
import heroes.domain.models.views.HeroDeleteViewModel;
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
public class DeleteHeroBean {

    private HeroDeleteViewModel heroDetail;

    private ModelMapper modelMapper;
    private HeroService heroService;

    public DeleteHeroBean() {
    }

    @Inject
    public DeleteHeroBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    public void load() {
        HeroServiceModel heroService = this.heroService.getById(this.getHeroId());
        this.heroDetail = this.modelMapper.map(heroService, HeroDeleteViewModel.class);
    }

    public HeroDeleteViewModel getHeroDetail() {
        return heroDetail;
    }

    public String remove() {
        String heroId = this.getHeroId();
        if (!this.heroService.removeById(heroId)) {
            return "pretty:heroDelete?heroId=" + heroId;
        }
        return "pretty:home";
    }

    private String getHeroId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return context.getRequestParameterMap().get("heroId");
    }
}
