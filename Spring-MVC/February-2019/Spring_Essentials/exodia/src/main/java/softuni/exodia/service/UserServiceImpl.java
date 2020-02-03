package softuni.exodia.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exodia.domain.entities.User;
import softuni.exodia.domain.models.service.UserServiceModel;
import softuni.exodia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        try {
            userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
            this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository
                        .getUserByUsername(userServiceModel.getUsername())
                        .orElse(null);

        String pass = DigestUtils.sha256Hex(userServiceModel.getPassword());
        if (user == null || !pass.equals(user.getPassword())) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
