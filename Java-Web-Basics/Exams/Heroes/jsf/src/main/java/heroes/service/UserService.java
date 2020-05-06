package heroes.service;

import heroes.domain.models.services.UserServiceModel;

public interface UserService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel loginUser(UserServiceModel userServiceModel);
}
