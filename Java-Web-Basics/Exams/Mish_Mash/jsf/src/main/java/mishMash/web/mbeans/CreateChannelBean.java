package mishMash.web.mbeans;

import mishMash.domain.enums.ChannelType;
import mishMash.domain.models.bindings.ChannelCreateBindingModel;
import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreateChannelBean {

    private ChannelCreateBindingModel channelCreateBM;
    private List<String> channelTypes;

    private ModelMapper modelMapper;
    private ChannelService channelService;

    public CreateChannelBean() {
    }

    @Inject
    public CreateChannelBean(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @PostConstruct
    public void init() {
        this.channelCreateBM = new ChannelCreateBindingModel();
    }

    public String createChannel() {
        ChannelServiceModel channelServiceModel = this.modelMapper
                .map(this.channelCreateBM, ChannelServiceModel.class);

        List<String> tags = Arrays.stream(this.channelCreateBM.getTags()
                .split(",\\s*"))
                .collect(Collectors.toList());

        channelServiceModel.setTags(tags);

        if (!this.channelService.createChannel(channelServiceModel)) {
            return "pretty:createChannel";
        }
        return "pretty:home";
    }

    public List<String> getChannelTypes() {
        if (this.channelTypes == null) {
            this.channelTypes = Arrays.stream(ChannelType.values())
                    .map(Enum::toString)
                    .collect(Collectors.toList());
        }

        return this.channelTypes;
    }

    public ChannelCreateBindingModel getChannelCreateBM() {
        return channelCreateBM;
    }
}
