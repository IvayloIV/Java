package mishMash.repository;

import mishMash.domain.entities.Channel;

public interface ChannelRepository extends GenericRepository<Channel, String> {

    public boolean update(Channel channel);
}
