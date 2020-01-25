package mishMash.web.mbeans;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.services.UserServiceModel;
import mishMash.domain.models.views.ChannelHomeViewModel;
import mishMash.service.ChannelService;
import mishMash.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ChannelHomeBean {

    private List<ChannelHomeViewModel> followedChannel;
    private List<ChannelHomeViewModel> suggestChannel;
    private List<ChannelHomeViewModel> otherChannel;

    private ModelMapper modelMapper;
    private ChannelService channelService;
    private UserService userService;

    public ChannelHomeBean() {
    }

    @Inject
    public ChannelHomeBean(ModelMapper modelMapper, ChannelService channelService, UserService userService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.followedChannel = new ArrayList<>();
        this.suggestChannel = new ArrayList<>();
        this.otherChannel = new ArrayList<>();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userId = (String) context.getSessionMap().get("id");
        UserServiceModel userServiceModel = this.userService.getById(userId);
        List<ChannelServiceModel> channels = this.channelService.getAll();

        for (ChannelServiceModel channel : channels) {
            List<ChannelHomeViewModel> channelType = getChannelType(userServiceModel, channel);
            channelType.add(this.modelMapper.map(channel, ChannelHomeViewModel.class));
        }
    }

    public String followChannel(String channelId) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userId = (String) context.getSessionMap().get("id");
        ChannelServiceModel channelServiceModel = this.channelService.getById(channelId);
        this.channelService.addNewFollower(channelServiceModel, userId);
        return "pretty:home";
    }

    public List<ChannelHomeViewModel> getFollowedChannel() {
        return followedChannel;
    }

    public List<ChannelHomeViewModel> getSuggestChannel() {
        return suggestChannel;
    }

    public List<ChannelHomeViewModel> getOtherChannel() {
        return otherChannel;
    }

    private List<ChannelHomeViewModel> getChannelType(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        if (this.isUserFollowChannel(userServiceModel, channel)) {
            return this.followedChannel;
        }

        if (this.checkTagConnection(userServiceModel, channel)) {
            return this.suggestChannel;
        }
        return this.otherChannel;
    }

    private boolean checkTagConnection(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        for (String tag : channel.getTags()) {
            if (checkUserTags(userServiceModel, tag)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUserFollowChannel(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        return userServiceModel.getFollowedChannels()
                .stream()
                .anyMatch(c -> c.getId().equals(channel.getId()));
    }

    private boolean checkUserTags(UserServiceModel userServiceModel, String tag) {
        return userServiceModel.getFollowedChannels()
                                .stream()
                                .anyMatch(u -> u.getTags()
                                                .stream()
                                                .anyMatch(t -> t.equals(tag)));
    }
}
