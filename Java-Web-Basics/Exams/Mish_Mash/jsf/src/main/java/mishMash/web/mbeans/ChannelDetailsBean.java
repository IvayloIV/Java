package mishMash.web.mbeans;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.views.ChannelDetailsViewModel;
import mishMash.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ChannelDetailsBean {

    private ChannelDetailsViewModel channelDetailsVM;

    private ModelMapper modelMapper;
    private ChannelService channelService;

    public ChannelDetailsBean() {
    }

    @Inject
    public ChannelDetailsBean(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String channelId = context.getRequestParameterMap().get("channelId");
        if (channelId != null) {
            ChannelServiceModel channel = this.channelService.getById(channelId);
            this.channelDetailsVM = this.modelMapper
                    .map(channel, ChannelDetailsViewModel.class);
            this.channelDetailsVM.setTags(String.join(", ", channel.getTags()));
        }
    }

    public ChannelDetailsViewModel getChannelDetailsVM() {
        return channelDetailsVM;
    }
}
