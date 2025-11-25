package com.example.demo.controller.admin;

import com.example.demo.domain.Image;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, ImageService imageService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user/create")
    public String getUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/createUser";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User dinhanh,
            BindingResult bindingResult, @RequestParam("avatarFile") MultipartFile file) {
        String password = dinhanh.getPassword();
        if (password == null || password.length() < 2 || password.length() > 20) {
            bindingResult.rejectValue("password", "error.password", "size must be between 2 and 20");
        }
        
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            System.out.println(">>>>: " + error.getField() + ", Message: " + error.getDefaultMessage());
        }
        if(bindingResult.hasErrors()){
            return "admin/user/createUser";
        }

        Image image = imageService.handleSaveImage(file);
        dinhanh.setAvatar(image);
        String hassPassord = this.passwordEncoder.encode(dinhanh.getPassword());
        dinhanh.setPassword(hassPassord);
        userService.handleSaveUser(dinhanh);
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

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.findById(id);
        Path path = Paths.get(image.getPath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    @PostMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @ModelAttribute("newUser") User user) {
        User curentUser = this.userService.getUserById(user.getId());
        if (curentUser != null) {
            curentUser.setFullName(user.getFullName());
            curentUser.setAddress(user.getAddress());
            curentUser.setPhoneNumber(user.getPhoneNumber());
            curentUser.setRoleId(user.getRoleId());
            curentUser.setAvatar(user.getAvatar());
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
