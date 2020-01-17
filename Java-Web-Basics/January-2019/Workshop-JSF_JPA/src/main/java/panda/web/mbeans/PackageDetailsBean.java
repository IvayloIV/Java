package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.models.views.PackageDetailsViewModel;
import panda.service.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PackageDetailsBean {

    private PackageDetailsViewModel packageDetailsVM;

    private ModelMapper modelMapper;
    private PackageService packageService;

    public PackageDetailsBean() {
    }

    @Inject
    public PackageDetailsBean(ModelMapper modelMapper, PackageService packageService) {
        this.modelMapper = modelMapper;
        this.packageService = packageService;
    }

    @PostConstruct
    public void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String packageId = context.getRequestParameterMap().get("packageId");
        this.packageDetailsVM = this.modelMapper
                .map(this.packageService.getById(packageId), PackageDetailsViewModel.class);
    }

    public PackageDetailsViewModel getPackageDetailsVM() {
        return packageDetailsVM;
    }
}
