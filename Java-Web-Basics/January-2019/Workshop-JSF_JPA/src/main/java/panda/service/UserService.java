package panda.service;

import panda.domain.models.services.UserServiceModel;

import java.util.List;

public interface UserService {

    public UserServiceModel save(UserServiceModel userServiceModel);

    public UserServiceModel login(UserServiceModel userServiceModel);

    public List<UserServiceModel> getAllUsers();

    public UserServiceModel getByUsername(String username);
}
