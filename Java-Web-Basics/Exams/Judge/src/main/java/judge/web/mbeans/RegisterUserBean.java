package judge.web.mbeans;

import org.modelmapper.ModelMapper;
import judge.domain.models.bindings.RegisterUserBindingModel;
import judge.domain.models.services.UserServiceModel;
import judge.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterUserBean {

    private RegisterUserBindingModel registerUserBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public RegisterUserBean() {
    }

    @Inject
    public RegisterUserBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void load() {
        this.registerUserBindingModel = new RegisterUserBindingModel();
    }

    public RegisterUserBindingModel getRegisterUserBindingModel() {
        return registerUserBindingModel;
    }

    public String register() {
        if (!registerUserBindingModel.getPassword()
                .equals(registerUserBindingModel.getConfirmPassword())) {
			return "pretty:register";
        }

        UserServiceModel userServiceModel = this.modelMapper
                    .map(this.registerUserBindingModel, UserServiceModel.class);

        if (!this.userService.registerUser(userServiceModel)) {
            return "pretty:register";
        }

        return "pretty:login";
    }
}
