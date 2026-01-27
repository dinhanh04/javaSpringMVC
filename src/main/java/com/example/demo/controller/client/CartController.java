package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.dto.OrderFormDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserRepository userRepository;

    public CartController(ProductService productService, OrderService orderService, UserRepository userRepository) {
        this.productService = productService;
        this.orderService = orderService;
        this.userRepository = userRepository;
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

    @PostMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable Long id, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        System.out.println(">>> Deleting cart item - CartDetailId: " + id + ", Email: " + email);

        if (email == null || email.isEmpty()) {
            System.out.println(">>> ERROR: User not logged in");
            return "redirect:/login";
        }

        try {
            this.productService.deleteCartDetail(id, email);
            System.out.println(">>> Cart item deleted successfully!");

            // Update cart sum in session
            Integer sum = this.productService.getCartSum(email);
            if (sum != null) {
                session.setAttribute("sum", sum);
                System.out.println(">>> Updated session sum: " + sum);
            }

            redirectAttributes.addFlashAttribute("message", "Đã xóa sản phẩm khỏi giỏ hàng");
        } catch (Exception e) {
            System.out.println(">>> ERROR deleting cart item: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Không thể xóa sản phẩm");
        }

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email == null || email.isEmpty()) {
            return "redirect:/login";
        }

        // Lấy thông tin giỏ hàng
        java.util.List<com.example.demo.dto.CartItemDTO> cartItems = this.productService.getCartItems(email);

        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        // Lấy thông tin user hiện tại
        User currentUser = this.userRepository.findByEmail(email);

        // Calculate totals
        double subtotal = cartItems.stream().mapToDouble(item -> item.getTotal()).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", subtotal);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("orderForm", new OrderFormDTO());

        return "client/cart/checkout";
    }

    @PostMapping("/checkout/place-order")
    public String placeOrder(@ModelAttribute("orderForm") OrderFormDTO orderForm,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email == null || email.isEmpty()) {
            return "redirect:/login";
        }

        try {
            Order order = this.orderService.createOrder(email, orderForm);

            // Update cart sum in session
            session.setAttribute("sum", 0);

            redirectAttributes.addFlashAttribute("message",
                    "Đặt hàng thành công! Mã đơn hàng: " + order.getId());

            return "redirect:/checkout/success/" + order.getId();
        } catch (Exception e) {
            System.out.println(">>> ERROR placing order: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error",
                    "Có lỗi xảy ra khi đặt hàng: " + e.getMessage());
            return "redirect:/checkout";
        }
    }

    @GetMapping("/checkout/success/{orderId}")
    public String getOrderSuccess(@PathVariable Long orderId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email == null || email.isEmpty()) {
            return "redirect:/login";
        }

        try {
            Order order = this.orderService.getOrderById(orderId);

            // Verify that the order belongs to the current user
            if (!order.getUser().getEmail().equals(email)) {
                return "redirect:/";
            }

            model.addAttribute("order", order);
            return "client/cart/success";
        } catch (Exception e) {
            System.out.println(">>> ERROR loading order: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/orders")
    public String getOrderHistory(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        if (email == null || email.isEmpty()) {
            return "redirect:/login";
        }

        java.util.List<Order> orders = this.orderService.getOrdersByUser(email);
        model.addAttribute("orders", orders);

        return "client/order/history";
    }
}
