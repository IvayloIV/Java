package casebook.service;

import casebook.domain.models.services.UserServiceModel;

import java.util.List;

public interface UserService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel loginUser(UserServiceModel userServiceModel);

    public List<UserServiceModel> getAll();

    public UserServiceModel getById(String userId);

    public void updateUser(UserServiceModel userServiceModel);
}
