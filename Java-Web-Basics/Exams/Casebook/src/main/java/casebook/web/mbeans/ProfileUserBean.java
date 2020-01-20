package casebook.web.mbeans;

import casebook.domain.models.views.ProfileUserViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProfileUserBean {

    private ProfileUserViewModel profileUserVM;

    private ModelMapper modelMapper;
    private UserService userService;

    public ProfileUserBean() {
    }

    @Inject
    public ProfileUserBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userId = context.getRequestParameterMap().get("userId");
        this.profileUserVM = this.modelMapper
                .map(this.userService.getById(userId), ProfileUserViewModel.class);
    }

    public ProfileUserViewModel getProfileUserVM() {
        return profileUserVM;
    }
}
