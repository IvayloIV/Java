package sboj.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import sboj.domain.entities.User;
import sboj.domain.models.services.UserServiceModel;
import sboj.repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.getUsername(userServiceModel.getUsername());
        String userPassword = userServiceModel.getPassword();

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
