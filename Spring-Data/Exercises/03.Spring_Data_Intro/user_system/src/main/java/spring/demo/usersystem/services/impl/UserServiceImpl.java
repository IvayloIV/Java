package spring.demo.usersystem.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.usersystem.dao.UserRepository;
import spring.demo.usersystem.models.User;
import spring.demo.usersystem.services.UserService;

import javax.persistence.ValidationMode;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Validator validator;
    private final UserRepository userRepository;

    public UserServiceImpl(Validator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Override
    public void checkValidation() {
        User user = new User();
        user.setUsername("Ivo44");
        user.setPassword("123456Aa@");
        user.setEmail("pesho123-pesho@gmail.com");
        user.setAge(3);

        Set<ConstraintViolation<User>> validations = validator.validate(user);
        validations.forEach(u -> logger.info(u.getMessage()));
    }

    @Override
    public void seedUsers() {
        userRepository.save(new User("ivan", "Ivan", "Ivanov", "123456Aa@", "pesho123-pesho@gmail.com", 32));
        userRepository.save(new User("Vanko", "Vanko", "Vankov", "123456Aa@", "vanko1-vanko1@gmail.com", 52));
        userRepository.save(new User("Gosho", "Gosho", "Petrov", "123456Aa@", "goshko_n00b-gn00b@gmail.com", 12));
        userRepository.save(new User("Penbo", "Penbo", "Pen", "123456Aa@", "penbo-pen@yahoo.co.uk", 54));
        userRepository.save(new User("CatLady", "Stefani", "Ivanova", "123456Aa@", "catLady-stephenyp@yahoo.co.uk", 15));
    }

    @Override
    public void printUserByEmail() {
        userRepository.findAllByEmailEndsWith("yahoo.co.uk")
            .forEach(u -> logger.info(u.getEmail()));
    }

    @Override
    public void setDeleteUserStatus() {
        LocalDate localDate = LocalDate.parse("12 Oct 2004", DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        List<User> users = userRepository.findAllByLastTimeLoggedInAfter(localDateTime);

        if (users.size() == 0) {
            logger.info("No users have been deleted.");
        } else {
            users.forEach(u -> {
                u.setIsDeleted(true);
                userRepository.save(u);
            });

            if (users.size() == 1) {
                logger.info("1 user has been deleted.");
            } else {
                logger.info("{} users have been deleted.", users.size());
            }
        }
    }

    @Transactional
    @Override
    public void deleteUsers() {
        userRepository.deleteAllByIsDeletedTrue();
    }
}
