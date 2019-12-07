package metubev3.domain.models.views;

import java.util.List;

public class UserProfileViewModel {
    private String username;
    private String email;
    private List<TubeProfileViewModel> tubes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TubeProfileViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubeProfileViewModel> tubes) {
        this.tubes = tubes;
    }
}
