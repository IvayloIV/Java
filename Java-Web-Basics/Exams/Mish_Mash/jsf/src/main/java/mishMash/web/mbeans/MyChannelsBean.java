package mishMash.web.mbeans;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.views.MyChannelViewModel;
import mishMash.service.ChannelService;
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
public class MyChannelsBean {

    private List<MyChannelViewModel> myChannelsVM;

    private ModelMapper modelMapper;
    private ChannelService channelService;

    public MyChannelsBean() {
    }

    @Inject
    public MyChannelsBean(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @PostConstruct
    private void init() {
        String userId = this.getUserId();
        this.myChannelsVM = this.channelService.getByUserId(userId)
            .stream()
            .map(c -> this.modelMapper.map(c, MyChannelViewModel.class))
            .collect(Collectors.toList());
        int a = 4;
    }

    public String unfollowChannel(String channelId) {
        String userId = this.getUserId();
        ChannelServiceModel channel = this.modelMapper
                .map(this.channelService.getById(channelId), ChannelServiceModel.class);
        this.channelService.removeFollower(channel, userId);
        return "pretty:myChannels";
    }

    private String getUserId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (String) context.getSessionMap().get("id");
    }

    public List<MyChannelViewModel> getMyChannelsVM() {
        return myChannelsVM;
    }
}
