package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.User;
import panda.domain.models.bindings.PackageBindingModel;
import panda.domain.models.services.PackageServiceModel;
import panda.domain.models.views.UserPackageCreateViewModel;
import panda.service.PackageService;
import panda.service.UserService;
import panda.util.UserConverter;

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
public class CreatePackageBean {

    private PackageBindingModel packageBindingModel;
    private List<UserPackageCreateViewModel> users;

    private ModelMapper modelMapper;
    private PackageService packageService;
    private UserService userService;

    public CreatePackageBean() {
    }

    @Inject
    public CreatePackageBean(ModelMapper modelMapper, PackageService packageService, UserService userService) {
        this.modelMapper = modelMapper;
        this.packageService = packageService;
        this.userService = userService;
        this.load();
    }

    private void load() {
        this.packageBindingModel = new PackageBindingModel();
        this.users = this.userService.getAllUsers()
                .stream()
                .map(u -> this.modelMapper.map(u, UserPackageCreateViewModel.class))
                .collect(Collectors.toList());
    }

    public void createPackage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        PackageServiceModel packageServiceModel = this.modelMapper.map(this.packageBindingModel, PackageServiceModel.class);

        if (!this.packageService.save(packageServiceModel)) {
            context.redirect("/faces/view/admin/createPackage.xhtml");
            return;
        }

        context.redirect("/faces/view/home.xhtml");
    }

    public UserPackageCreateViewModel getUser(String id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (UserPackageCreateViewModel user : users){
            if (id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

    public PackageBindingModel getPackageBindingModel() {
        return packageBindingModel;
    }

    public void setPackageBindingModel(PackageBindingModel packageBindingModel) {
        this.packageBindingModel = packageBindingModel;
    }

    public List<UserPackageCreateViewModel> getUsers() {
        return users;
    }
}
