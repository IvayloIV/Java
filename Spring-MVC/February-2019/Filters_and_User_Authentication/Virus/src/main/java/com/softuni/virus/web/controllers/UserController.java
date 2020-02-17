package com.softuni.virus.web.controllers;

import com.softuni.virus.domain.models.binding.UserRegisterBindingModel;
import com.softuni.virus.domain.models.service.UserServiceModel;
import com.softuni.virus.domain.models.view.RoleUsersViewModel;
import com.softuni.virus.domain.models.view.UserViewModel;
import com.softuni.virus.service.RoleService;
import com.softuni.virus.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RoleService roleService;

    private RoleUsersViewModel rootRole;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute("rootRole")
    public RoleUsersViewModel getRootRole() {
        if (this.rootRole == null) {
            this.rootRole = this.modelMapper
                    .map(this.roleService.findByAuthority("ROOT"), RoleUsersViewModel.class);
        }
        return this.rootRole;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("user/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute(name = "userModel") UserRegisterBindingModel userModel) {
        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            return super.redirect("/user/register");
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(userModel, UserServiceModel.class);

        if (!this.userService.registerUser(userServiceModel)) {
            return super.redirect("/user/register");
        }

        return super.redirect("/user/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("user/login");
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<UserViewModel> users = this.userService.getAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());

        List<RoleUsersViewModel> roles = this.roleService.getAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleUsersViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);
        modelAndView.addObject("roles", roles);
        return super.view("user/all", modelAndView);
    }

    @GetMapping("/authorize/{userId}/role/{roleId}")
    public ModelAndView changeRole(@PathVariable(name = "userId") String userId,
                                @PathVariable(name = "roleId") String roleId) {
        this.userService.changeRole(userId, roleId);
        return super.redirect("/user/all");
    }
}
