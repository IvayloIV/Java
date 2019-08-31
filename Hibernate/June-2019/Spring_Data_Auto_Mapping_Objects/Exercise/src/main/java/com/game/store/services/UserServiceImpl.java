package com.game.store.services;

import com.game.store.domain.dtos.UserLoginDto;
import com.game.store.domain.dtos.UserRegisterDto;
import com.game.store.domain.entities.Role;
import com.game.store.domain.entities.User;
import com.game.store.repositories.UserRepository;
import com.game.store.services.Base.GameService;
import com.game.store.services.Base.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GameService gameService;
    private String loggedUser;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameService gameService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
        this.modelMapper = new ModelMapper();
    }

    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();
        User user = this.modelMapper.map(userRegisterDto, User.class);

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return sb.append("Passwords must match.").toString();
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.size() > 0) {
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User userDb = this.userRepository.findByEmail(user.getEmail()).orElse(null);

            if (userDb != null) {
                return sb.append("User email already exist.").toString();
            }

            long countUsers = this.userRepository.count();
            if (countUsers == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }

            this.userRepository.saveAndFlush(user);
            sb.append(String.format("%s was registered", user.getFullName()));
        }

        return sb.toString().trim();
    }

    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        if (this.loggedUser != null) {
            return sb.append("User already logged.").toString();
        }

        User user = this.userRepository.findByEmail(userLoginDto.getEmail())
                .orElse(null);

        if (user == null) {
            return sb.append("User with this email not exist.").toString();
        }

        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            return sb.append("Incorrect username / password.").toString();
        }

        this.loggedUser = user.getEmail();
        this.gameService.changeUser(user);
        sb.append(String.format("Successfully logged in %s", user.getFullName()));
        return sb.toString();
    }

    public String logout() {
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser == null) {
            return sb.append("Cannot log out. No user was logged in.").toString();
        }

        User user = this.userRepository.findByEmail(this.loggedUser)
                .orElse(null);

        this.loggedUser = null;
        this.gameService.changeUser(null);
        return String.format("User %s successfully logged out", user.getFullName());
    }
}
