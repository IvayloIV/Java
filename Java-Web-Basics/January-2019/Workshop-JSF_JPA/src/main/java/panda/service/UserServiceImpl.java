package panda.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import panda.domain.entities.User;
import panda.domain.enums.Role;
import panda.domain.models.services.UserServiceModel;
import panda.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel save(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        if (this.userRepository.count() == 0) {
            user.setRole(Role.Admin);
        } else {
            user.setRole(Role.User);
        }

        if (this.userRepository.save(user)) {
            return this.modelMapper.map(user, UserServiceModel.class);
        } else {
            return null;
        }
    }

    @Override
    public UserServiceModel login(UserServiceModel userServiceModel) {
        User user = this.userRepository.getByUsername(userServiceModel.getUsername());

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()))) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.getAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        return this.modelMapper
                .map(this.userRepository.getByUsername(username), UserServiceModel.class);
    }
}
