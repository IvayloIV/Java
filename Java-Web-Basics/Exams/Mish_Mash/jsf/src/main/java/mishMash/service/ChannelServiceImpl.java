package mishMash.service;

import mishMash.domain.entities.Channel;
import mishMash.domain.entities.User;
import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.services.UserServiceModel;
import mishMash.repository.ChannelRepository;
import mishMash.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelServiceImpl implements ChannelService {

    private ModelMapper modelMapper;
    private ChannelRepository channelRepository;
    private UserRepository userRepository;

    @Inject
    public ChannelServiceImpl(ModelMapper modelMapper, ChannelRepository channelRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createChannel(ChannelServiceModel channelServiceModel) {
        return this.channelRepository
                .save(this.modelMapper.map(channelServiceModel, Channel.class));
    }

    @Override
    public List<ChannelServiceModel> getAll() {
        return this.channelRepository.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, ChannelServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewFollower(ChannelServiceModel channelServiceModel, String userId) {
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userRepository.getById(userId), UserServiceModel.class);
        channelServiceModel.getFollowers().add(userServiceModel);

        this.channelRepository
                .update(this.modelMapper.map(channelServiceModel, Channel.class));
    }

    @Override
    public void removeFollower(ChannelServiceModel channelServiceModel, String userId) {
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userRepository.getById(userId), UserServiceModel.class);
        channelServiceModel.getFollowers().removeIf(u -> u.getId().equals(userServiceModel.getId()));

        this.channelRepository
                .update(this.modelMapper.map(channelServiceModel, Channel.class));
    }

    @Override
    public ChannelServiceModel getById(String channelId) {
        return this.modelMapper
                .map(this.channelRepository.getById(channelId), ChannelServiceModel.class);
    }

    @Override
    public List<ChannelServiceModel> getByUserId(String userId) {
        return this.userRepository.getById(userId)
                .getFollowedChannels()
                .stream()
                .map(c -> this.modelMapper.map(c, ChannelServiceModel.class))
                .collect(Collectors.toList());
    }
}
