package mishMash.domain.models.views;

import mishMash.domain.enums.ChannelType;

import java.util.List;

public class MyChannelViewModel {

    private String id;

    private String name;

    private ChannelType type;

    private List<UserHomeViewModel> followers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public List<UserHomeViewModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserHomeViewModel> followers) {
        this.followers = followers;
    }
}
