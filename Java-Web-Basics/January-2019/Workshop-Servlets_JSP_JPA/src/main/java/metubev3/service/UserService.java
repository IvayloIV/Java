package metubev3.service;

import metubev3.domain.models.services.UserServiceModel;

public interface UserService {
    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel loginUser(UserServiceModel userServiceModel);

    public UserServiceModel findByUsername(String username);
}
