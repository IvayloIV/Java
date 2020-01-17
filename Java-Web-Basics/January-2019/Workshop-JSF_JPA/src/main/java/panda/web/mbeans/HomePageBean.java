package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Role;
import panda.domain.models.services.PackageServiceModel;
import panda.domain.models.views.PackageHomeViewModel;
import panda.service.PackageService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomePageBean {
    
    private List<PackageHomeViewModel> packages;
    
    private ModelMapper modelMapper;
    private PackageService packageService;
    private UserService userService;

    public HomePageBean() {
        this.packages = new ArrayList<>();
    }

    @Inject
    public HomePageBean(ModelMapper modelMapper, PackageService packageService, UserService userService) {
        this();
        this.modelMapper = modelMapper;
        this.packageService = packageService;
        this.userService = userService;
        this.load();
    }

    private void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        List<PackageServiceModel> packagesServiceModel = null;
        if (context.getSessionMap().get("role").equals(Role.Admin)) {
            packagesServiceModel = this.packageService.getAllPackages();
        } else {
            String username = (String) context.getSessionMap().get("username");
            packagesServiceModel = this.userService.getByUsername(username).getPackages();
        }

        this.packages = packagesServiceModel.stream()
                .map(p -> this.modelMapper.map(p, PackageHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageHomeViewModel> getPackages() {
        return packages;
    }
}
