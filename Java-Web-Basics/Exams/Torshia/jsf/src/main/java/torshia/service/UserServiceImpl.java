package torshia.service;

import torshia.domain.enums.Role;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import torshia.domain.entities.User;
import torshia.domain.models.services.UserServiceModel;
import torshia.repository.UserRepository;

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

        if (this.userRepository.size() == 0) {
            user.setRole(Role.Admin);
        } else {
            user.setRole(Role.User);
        }
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

    @Override
    public UserServiceModel getById(String userId) {
        return this.modelMapper
                .map(this.userRepository.getById(userId), UserServiceModel.class);
    }
}
