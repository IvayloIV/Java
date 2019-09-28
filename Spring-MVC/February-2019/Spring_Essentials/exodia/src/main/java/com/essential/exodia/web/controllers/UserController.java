package com.essential.exodia.web.controllers;

import com.essential.exodia.domain.models.UserLoginBindingModel;
import com.essential.exodia.domain.models.UserRegisterBindingModel;
import com.essential.exodia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/schedule/home");
            return modelAndView;
        }

        modelAndView.addObject("session", httpSession);
        return super.view("user/register", modelAndView);
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute(name = "model") UserRegisterBindingModel userRegisterBindingModel) {
        try {
            this.userService.registerUser(userRegisterBindingModel);
        } catch (IllegalArgumentException e) {
            return super.redirect("/register");
        }
        return super.redirect("/login");
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/schedule/home");
            return modelAndView;
        }

        modelAndView.addObject("session", httpSession);
        return super.view("user/login", modelAndView);
    }

    @PostMapping("/login")
    public String loginConfirm(@ModelAttribute(name = "model")UserLoginBindingModel userLoginBindingModel,
                               HttpSession httpSession) {
        try {
            String userId = this.userService.loginUser(userLoginBindingModel);
            httpSession.setAttribute("userId", userId);
        } catch (IllegalArgumentException e) {
            return super.redirect("/login");
        }
        return super.redirect("/schedule/home");
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/";
        }

        httpSession.invalidate();
        return super.redirect("/");
    }
}
