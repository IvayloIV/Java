package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.models.bindings.UserRegisterBindingModel;
import panda.domain.models.services.UserServiceModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterUserBean {

    private UserRegisterBindingModel userRegisterBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public RegisterUserBean() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public RegisterUserBean(ModelMapper modelMapper, UserService userService) {
        this();
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void registerUser() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        String password = this.userRegisterBindingModel.getPassword();
        String confirmPassword = this.userRegisterBindingModel.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            context.redirect("/faces/view/register.xhtml");
            return;
        }

        this.userService.save(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        context.redirect("/faces/view/login.xhtml");
    }
}
