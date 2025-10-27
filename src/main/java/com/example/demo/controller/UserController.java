package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//public class UserController {
//
//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String getHomePage(){
//        return this.userService.handleService();
//    }
//}
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user/create")
    public String getUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/createUser";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User dinhanh) {
        System.out.println("User info: " + dinhanh);
        this.userService.handleSaveUser(dinhanh);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        System.out.println("check user list: " + users);
        return "admin/user/tableUser";
    }
}
