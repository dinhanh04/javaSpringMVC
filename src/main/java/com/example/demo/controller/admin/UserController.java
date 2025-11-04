package com.example.demo.controller.admin;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
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
        this.userService.handleSaveUser(dinhanh);
        System.out.println("User info: " + dinhanh);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        System.out.println("check user list: " + users);
        return "admin/user/show";
    }


    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserById(Model model, @PathVariable Long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user2", user);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUser(Model model, @PathVariable Long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("newUser", user);
        return "admin/user/updateUser";
    }

    @PostMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @ModelAttribute("newUser") User user) {
        User curentUser = this.userService.getUserById(user.getId());
        if (curentUser != null) {
            curentUser.setFullName(user.getFullName());
            curentUser.setAddress(user.getAddress());
            curentUser.setPhoneNumber(user.getPhoneNumber());
            this.userService.handleSaveUser(curentUser);
        }
        return "redirect:/admin/user";
    }

    // Mở trang xác nhận
    @GetMapping("/admin/user/delete/{id}")
    public String showDelete(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user/deleteUser";
    }

    // Thực hiện xoá
    @PostMapping("/admin/user/delete/{id}")
    public String doDelete(@PathVariable Long id, RedirectAttributes ra) {
        userService.deleteUserById(id);
        ra.addFlashAttribute("success", "Deleted user #" + id);
        return "redirect:/admin/user";
    }



    @Bean
    ApplicationRunner dsCheck(DataSource ds) {
        return args -> System.out.println("JDBC URL = " + ds.getConnection().getMetaData().getURL());
    }

}
