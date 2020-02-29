package com.producthop.softuni.web.controllers;

import com.producthop.softuni.domain.models.bindings.RegisterUserBindingModel;
import com.producthop.softuni.domain.models.bindings.UserEditBindingModel;
import com.producthop.softuni.domain.models.services.RoleServiceModel;
import com.producthop.softuni.domain.models.services.UserServiceModel;
import com.producthop.softuni.domain.models.views.RoleUserViewModel;
import com.producthop.softuni.domain.models.views.UserDetailsViewModel;
import com.producthop.softuni.domain.models.views.UserProfileViewModel;
import com.producthop.softuni.service.RoleService;
import com.producthop.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("user/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute RegisterUserBindingModel registerModel) {
        if (!registerModel.getPassword().equals(registerModel.getConfirmPassword())) {
            return super.redirect("/user/register");
        }
        UserServiceModel userServiceModel = this.modelMapper
                .map(registerModel, UserServiceModel.class);
        boolean isSaved = this.userService.registerUser(userServiceModel);

        if (!isSaved) {
            return super.redirect("/user/register");
        }
        return super.redirect("/user/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("user/login");
    }

    @GetMapping("/profile")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        String username = principal.getName();
        UserProfileViewModel userView = this.modelMapper
                .map(this.userService.profileUser(username), UserProfileViewModel.class);

        modelAndView.addObject("user", userView);
        return super.view("user/profile", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        UserProfileViewModel userView = this.modelMapper
                .map(this.userService.getById(id), UserProfileViewModel.class);

        modelAndView.addObject("user", userView);
        return super.view("user/edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editConfirm(@ModelAttribute UserEditBindingModel userEditBindingModel,
                                    @PathVariable(name = "id") String id) {
        if (!userEditBindingModel.getPassword()
                .equals(userEditBindingModel.getConfirmPassword())) {
            return super.redirect("/user/edit/" + id);
        }

        boolean userSaved = this.userService.editUser(this.modelMapper.map(userEditBindingModel,
                UserServiceModel.class), userEditBindingModel.getOldPassword());

        if (!userSaved) {
            return super.redirect("/user/edit/" + id);
        }
        return super.redirect("/user/profile");
    }

    @GetMapping("/all")
    public ModelAndView listAllUsers(ModelAndView modelAndView) {
        List<UserDetailsViewModel> users = this.userService.getAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserDetailsViewModel.class))
                .collect(Collectors.toList());

        List<RoleUserViewModel> roles = this.roleService.getAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleUserViewModel.class))
                .filter(r -> !r.getAuthority().equals("ROOT"))
                .collect(Collectors.toList());

        RoleUserViewModel rootRole = this.modelMapper
                .map(this.roleService.getByAuthority("ROOT"), RoleUserViewModel.class);

        modelAndView.addObject("users", users);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("rootRole", rootRole);
        return super.view("user/all", modelAndView);
    }

    @PostMapping("/{userId}/role/{roleId}")
    public ModelAndView addRole(@PathVariable String userId,
                                @PathVariable String roleId) {
        this.userService.addRole(userId, roleId);
        return super.redirect("/user/all");
    }
}
