package casebook.service;

import casebook.domain.entities.User;
import casebook.domain.models.services.UserServiceModel;
import casebook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

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

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.getAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getById(String userId) {
        return this.modelMapper
                .map(this.userRepository.getById(userId), UserServiceModel.class);
    }

    @Override
    public void updateUser(UserServiceModel userServiceModel) {
        this.userRepository
                .updateUser(this.modelMapper.map(userServiceModel, User.class));
    }
}
