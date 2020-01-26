package mishMash.domain.models.views;

import java.util.List;

public class ChannelHomeViewModel {

    private String id;

    private String name;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserHomeViewModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserHomeViewModel> followers) {
        this.followers = followers;
    }
}
