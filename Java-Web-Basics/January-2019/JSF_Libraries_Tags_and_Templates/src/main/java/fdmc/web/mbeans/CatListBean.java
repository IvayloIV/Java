package fdmc.web.mbeans;

import fdmc.domain.model.view.CatListViewModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatListBean {

    private List<CatListViewModel> catListViewModel;

    private CatService catService;
    private ModelMapper modelMapper;

    public CatListBean() {
    }

    @Inject
    public CatListBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init() {
        this.catListViewModel = this.catService.getAll()
            .stream()
            .map(cat -> this.modelMapper.map(cat, CatListViewModel.class))
            .collect(Collectors.toList());
    }

    public List<CatListViewModel> getCatListViewModel() {
        return catListViewModel;
    }
}
