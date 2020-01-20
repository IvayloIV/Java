package casebook.web.mbeans;

import casebook.domain.models.views.UserFriendsViewModel;
import casebook.domain.models.services.UserServiceModel;
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
public class FriendsBean {

    private List<UserFriendsViewModel> friendsVM;

    private ModelMapper modelMapper;
    private UserService userService;

    public FriendsBean() {
    }

    @Inject
    public FriendsBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.friendsVM = this.userService.getById(this.getSessionUserId())
            .getFriends()
            .stream()
            .map(f -> this.modelMapper.map(f, UserFriendsViewModel.class))
            .collect(Collectors.toList());
    }

    public String removeFriend(String friendId) {
        String sessionUserId = this.getSessionUserId();
        UserServiceModel sessionUser = this.userService.getById(sessionUserId);

        sessionUser.setFriends(sessionUser.getFriends()
                .stream()
                .filter(f -> !f.getId().equals(friendId))
                .collect(Collectors.toList()));
        this.userService.updateUser(sessionUser);
        return "pretty:friends";
    }

    public List<UserFriendsViewModel> getFriendsVM() {
        return friendsVM;
    }

    private String getSessionUserId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return  (String) context.getSessionMap().get("id");
    }
}
