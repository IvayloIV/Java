package judge.web.mbeans;

import org.modelmapper.ModelMapper;
import judge.domain.models.bindings.LoginUserBindingModel;
import judge.domain.models.services.UserServiceModel;
import judge.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginUserBean {

    private LoginUserBindingModel loginUserBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public LoginUserBean() {
    }

    @Inject
    public LoginUserBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void load() {
        this.loginUserBindingModel = new LoginUserBindingModel();
    }

    public LoginUserBindingModel getLoginUserBindingModel() {
        return loginUserBindingModel;
    }

    public String login() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.loginUserBindingModel, UserServiceModel.class);

        UserServiceModel user = this.userService.loginUser(userServiceModel);
        if (user == null) {
			return "pretty:login";
        }

        HttpSession session = (HttpSession) context.getSession(false);
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());

		return "pretty:home";
    }
}
