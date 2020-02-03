package softuni.exodia.service;

import softuni.exodia.domain.models.service.UserServiceModel;

public interface UserService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel loginUser(UserServiceModel userServiceModel);
}
