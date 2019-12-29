package fdmc.web.mbeans;

import fdmc.domain.model.binding.CatCreateBindingModel;
import fdmc.domain.model.service.CatServiceModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CreateCatBean {

    private CatCreateBindingModel catCreateBindingModel;

    private ModelMapper modelMapper;
    private CatService catService;

    public CreateCatBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CreateCatBean(ModelMapper modelMapper, CatService catService) {
        this();
        this.modelMapper = modelMapper;
        this.catService = catService;
    }

    public void createCat() throws IOException {
        boolean isSaved = this.catService
                .save(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (isSaved) {
            context.redirect("/");
        }
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }
}
