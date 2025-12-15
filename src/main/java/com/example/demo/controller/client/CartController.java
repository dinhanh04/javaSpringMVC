package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(HttpServletRequest request, @PathVariable Long id) {
        HttpSession session = request.getSession(false);

        long productId = id;
        String email = (String) session.getAttribute("email");

        System.out.println(">>> Adding product to cart - Email: " + email + ", ProductId: " + productId);

        if (email == null || email.isEmpty()) {
            System.out.println(">>> ERROR: Email is null! User not logged in.");
            return "redirect:/login";
        }

        try {
            this.productService.addProductToCart(email, productId);
            System.out.println(">>> Product added successfully!");

            // Update cart sum in session
            Integer sum = this.productService.getCartSum(email);
            if (sum != null) {
                session.setAttribute("sum", sum);
                System.out.println(">>> Updated session sum: " + sum);
            }
        } catch (Exception e) {
            System.out.println(">>> ERROR adding product to cart: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email != null) {
            java.util.List<com.example.demo.dto.CartItemDTO> cartItems = this.productService.getCartItems(email);
            model.addAttribute("cartItems", cartItems);

            // Calculate totals
            double subtotal = cartItems.stream().mapToDouble(item -> item.getTotal()).sum();
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("total", subtotal); // Can add shipping later
        } else {
            model.addAttribute("cartItems", new java.util.ArrayList<>());
            model.addAttribute("subtotal", 0.0);
            model.addAttribute("total", 0.0);
        }

        return "client/cart/cart";
    }
}
