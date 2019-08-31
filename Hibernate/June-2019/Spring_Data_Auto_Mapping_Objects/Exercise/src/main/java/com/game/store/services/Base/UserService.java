package com.game.store.services.Base;

import com.game.store.domain.dtos.UserLoginDto;
import com.game.store.domain.dtos.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logout();
}
