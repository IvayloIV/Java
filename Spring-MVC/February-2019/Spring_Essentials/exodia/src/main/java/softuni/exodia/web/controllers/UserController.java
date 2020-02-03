package softuni.exodia.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exodia.domain.models.binding.UserLoginBindingModel;
import softuni.exodia.domain.models.binding.UserRegisterBindingModel;
import softuni.exodia.domain.models.service.UserServiceModel;
import softuni.exodia.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        return this.view("user/register", modelAndView);
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute UserRegisterBindingModel registerModel) {
        if (!registerModel.getPassword().equals(registerModel.getConfirmPassword())) {
            return this.redirect("/user/register");
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(registerModel, UserServiceModel.class);
        if(!this.userService.registerUser(userServiceModel)) {
            return this.redirect("/user/register");
        }

        return this.redirect("/user/login");
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        return this.view("user/login", modelAndView);
    }

    @PostMapping("/login")
    public String loginConfirm(@ModelAttribute UserLoginBindingModel loginModel,
                                HttpSession httpSession) {
        UserServiceModel userServiceModel = this.modelMapper
                .map(loginModel, UserServiceModel.class);
        UserServiceModel user = this.userService.loginUser(userServiceModel);

        if (user == null) {
            return this.redirect("/user/login");
        }
        httpSession.setAttribute("userId", user.getId());
        httpSession.setAttribute("username", user.getUsername());

        return this.redirect("/document/home");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return this.redirect("/");
    }
}
