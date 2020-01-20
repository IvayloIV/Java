package casebook.web.mbeans;

import casebook.domain.entities.User;
import casebook.domain.models.services.UserServiceModel;
import casebook.domain.models.views.UserHomeViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<UserHomeViewModel> usersVM;

    private ModelMapper modelMapper;
    private UserService userService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        String userId = this.getSessionUserId();
        UserServiceModel user = this.userService.getById(userId);
        this.usersVM = this.userService.getAll()
                .stream()
                .filter(u -> user.getFriends().stream().noneMatch(f -> f.getId().equals(u.getId()))
                            && !u.getId().equals(userId))
                .map(u -> this.modelMapper.map(u, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserHomeViewModel> getUsersVM() {
        return usersVM;
    }

    public String addNewFriend(String friendId) {
        String sessionUserId = this.getSessionUserId();
        UserServiceModel sessionUser = this.userService.getById(sessionUserId);
        UserServiceModel newFriend = this.userService.getById(friendId);

        sessionUser.getFriends().add(newFriend);
        this.userService.updateUser(sessionUser);
        return "pretty:home";
    }

    private String getSessionUserId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return  (String) context.getSessionMap().get("id");
    }
}
