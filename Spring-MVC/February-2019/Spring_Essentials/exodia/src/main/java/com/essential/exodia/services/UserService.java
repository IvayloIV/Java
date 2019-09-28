package com.essential.exodia.services;

import com.essential.exodia.domain.models.UserLoginBindingModel;
import com.essential.exodia.domain.models.UserRegisterBindingModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    String loginUser(UserLoginBindingModel userLoginBindingModel);
}
