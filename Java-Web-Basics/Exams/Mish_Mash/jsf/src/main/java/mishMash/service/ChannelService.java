package mishMash.service;

import mishMash.domain.models.services.ChannelServiceModel;

import java.util.List;

public interface ChannelService {

    public boolean createChannel(ChannelServiceModel channelServiceModel);

    public List<ChannelServiceModel> getAll();

    public void addNewFollower(ChannelServiceModel channelServiceModel, String userId);

    public void removeFollower(ChannelServiceModel channelServiceModel, String userId);

    public ChannelServiceModel getById(String channelId);

    public List<ChannelServiceModel> getByUserId(String userId);
}
