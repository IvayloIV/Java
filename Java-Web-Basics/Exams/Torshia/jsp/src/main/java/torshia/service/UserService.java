package torshia.service;

import torshia.domain.models.services.UserServiceModel;

public interface UserService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel loginUser(UserServiceModel userServiceModel);

    public UserServiceModel getById(String userId);
}
