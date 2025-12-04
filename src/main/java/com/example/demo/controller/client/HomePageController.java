package com.example.demo.controller.client;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @Valid @ModelAttribute("registerUser") RegisterDTO registerDTO,
            BindingResult bindingResult,
            Model model) {

        // Debug xem có nhận được data không
        System.out.println("===== DEBUG REGISTER DTO =====");
        System.out.println("FirstName: " + registerDTO.getFirstName());
        System.out.println("LastName : " + registerDTO.getLastName());
        System.out.println("Email    : " + registerDTO.getEmail());
        System.out.println("Password : " + registerDTO.getPassword());
        System.out.println("Confirm  : " + registerDTO.getConfirmPassword());

        // Validate password match
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.rejectValue(
                    "confirmPassword",
                    "error.registerUser",
                    "Mật khẩu xác nhận không khớp"
            );
        }

        // Check if email already exists
        if (userService.checkEmailExist(registerDTO.getEmail())) {
            bindingResult.rejectValue(
                    "email",
                    "error.registerUser",
                    "Email đã được sử dụng"
            );
        }

        // Nếu có lỗi -> quay lại form
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        // Tạo user mới
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setRoleId(1L);

        userService.handleSaveUser(user);

        return "redirect:/login?registered";
    }
}
