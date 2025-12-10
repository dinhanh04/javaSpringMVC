package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.service.UserService;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("registerUser") RegisterDTO registerDTO,
            Model model) {

        // Validate password confirmation
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            model.addAttribute("errorMessage", "Mật khẩu xác nhận không khớp");
            model.addAttribute("registerUser", registerDTO);
            return "client/auth/register";
        }

        // Check if email already exists
        if (this.userService.checkEmailExist(registerDTO.getEmail())) {
            model.addAttribute("errorMessage", "Email đã tồn tại");
            model.addAttribute("registerUser", registerDTO);
            return "client/auth/register";
        }

        // Create new user
        User user = this.userService.registerDtoToUser(registerDTO);
        // Encode password
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        // Set default role (assuming role ID 1 is USER)
        user.setRoleId(1L);
        
        // Save user
        this.userService.handleSaveUser(user);
        
        return "redirect:/login?registered";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
        return "client/auth/deny";
    }

    // Endpoint tạm để tạo user test - XÓA SAU KHI TEST XONG
    @GetMapping("/create-test-user")
    public String createTestUser() {
        // Kiểm tra xem user đã tồn tại chưa
        if (!this.userService.checkEmailExist("test@test.com")) {
            User user = new User();
            user.setEmail("test@test.com");
            user.setPassword(this.passwordEncoder.encode("123456"));
            user.setFullName("Test User");
            user.setRoleId(1L);
            this.userService.handleSaveUser(user);
            return "redirect:/login?created";
        }
        return "redirect:/login?exists";
    }
}