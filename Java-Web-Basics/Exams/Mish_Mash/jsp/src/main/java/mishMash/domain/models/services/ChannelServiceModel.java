package mishMash.domain.models.services;

import mishMash.domain.enums.ChannelType;

import java.util.List;

public class ChannelServiceModel {

    private String id;

    private String name;

    private String description;

    private ChannelType type;

    private List<String> tags;

    private List<UserServiceModel> followers;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<UserServiceModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserServiceModel> followers) {
        this.followers = followers;
    }
}
