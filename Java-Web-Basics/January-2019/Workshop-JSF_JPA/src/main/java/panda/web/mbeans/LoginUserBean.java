package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.models.bindings.UserLoginBindingModel;
import panda.domain.models.services.UserServiceModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class LoginUserBean {

    private UserLoginBindingModel userLoginBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public LoginUserBean() {
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    @Inject
    public LoginUserBean(ModelMapper modelMapper, UserService userService) {
        this();
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel userServiceModel = this.userService.login(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));

        if (userServiceModel == null) {
            context.redirect("/faces/view/login.xhtml");
            return;
        }

        HttpSession session = (HttpSession) context.getSession(false);
        session.setAttribute("username", userServiceModel.getUsername());
        session.setAttribute("role", userServiceModel.getRole());
        context.redirect("/faces/view/home.xhtml");
    }
}
