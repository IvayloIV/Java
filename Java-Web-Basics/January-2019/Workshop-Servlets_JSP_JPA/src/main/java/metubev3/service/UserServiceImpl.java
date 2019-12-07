package metubev3.service;

import metubev3.domain.entities.User;
import metubev3.domain.enums.UserRole;
import metubev3.domain.models.services.UserServiceModel;
import metubev3.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        if (this.userRepository.size() == 0) {
            userServiceModel.setRole(UserRole.Admin);
        } else {
            userServiceModel.setRole(UserRole.User);
        }

        return this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map(this.userRepository.login(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }
}
