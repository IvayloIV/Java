package mishMash.domain.models.views;

import mishMash.domain.enums.ChannelType;

import java.util.List;

public class ChannelDetailsViewModel {

    private String name;

    private ChannelType type;

    private List<UserHomeViewModel> followers;

    private String tags;

    private String description;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
