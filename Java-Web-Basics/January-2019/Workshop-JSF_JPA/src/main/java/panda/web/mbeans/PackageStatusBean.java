package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Status;
import panda.domain.models.views.PackageStatusViewModel;
import panda.service.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageStatusBean {

    private List<PackageStatusViewModel> packagesStatusViewModel;

    private ModelMapper modelMapper;
    private PackageService packageService;

    public PackageStatusBean() {
    }

    @Inject
    public PackageStatusBean(ModelMapper modelMapper, PackageService packageService) {
        this.modelMapper = modelMapper;
        this.packageService = packageService;
    }

    @PostConstruct
    private void load() {
        Status status = this.getStatus();
        this.packagesStatusViewModel = this.packageService.getAllByStatus(status)
                .stream()
                .map(p -> this.modelMapper.map(p, PackageStatusViewModel.class))
                .collect(Collectors.toList());
    }

    public void changeStatus(String id) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Status status = Status.valueOf(context.getRequestParameterMap().get("status"));
        this.packageService.changeStatus(id, status.next());

        if (status.next().equals(Status.Acquired)) {
            context.redirect("/faces/view/home.xhtml");
        } else {
            context.redirect("/faces/view/admin/packageStatus.xhtml?status=" + status);
        }
    }

    public Status getStatus() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return Status.valueOf(context.getRequestParameterMap().get("status"));
    }

    public Status getNextStatus() {
        return this.getStatus().next();
    }

    public List<PackageStatusViewModel> getPackagesStatusViewModel() {
        return packagesStatusViewModel;
    }
}
