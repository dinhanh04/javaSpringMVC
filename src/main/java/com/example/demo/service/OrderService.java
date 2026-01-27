package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.OrderFormDTO;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productService = productService;
    }

    @Transactional
    public Order createOrder(String userEmail, OrderFormDTO orderForm) {
        // Lấy thông tin user
        User user = this.userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Lấy cart items
        List<CartItemDTO> cartItems = this.productService.getCartItems(userEmail);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Tạo order mới
        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(orderForm.getReceiverName());
        order.setReceiverPhone(orderForm.getReceiverPhone());
        order.setReceiverAddress(orderForm.getReceiverAddress());
        order.setNote(orderForm.getNote());
        order.setStatus(Order.OrderStatus.PENDING);

        // Tính tổng tiền
        double totalPrice = cartItems.stream().mapToDouble(CartItemDTO::getTotal).sum();
        order.setTotalPrice(totalPrice);

        // Lưu order
        order = this.orderRepository.save(order);

        // Tạo order details
        for (CartItemDTO cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);

            Product product = this.productRepository.findById(cartItem.getProductId()).orElse(null);
            if (product != null) {
                orderDetail.setProduct(product);
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setPrice(cartItem.getPrice());

                this.orderDetailRepository.save(orderDetail);
            }
        }

        // Xóa cart sau khi đặt hàng thành công
        this.clearCart(userEmail);

        return order;
    }

    @Transactional
    private void clearCart(String userEmail) {
        User user = this.userRepository.findByEmail(userEmail);
        if (user != null) {
            Cart cart = this.cartRepository.findByUserId(user.getId());
            if (cart != null) {
                // Xóa tất cả cart details
                this.cartDetailRepository.deleteByCartId(cart.getId());

                // Reset cart sum
                cart.setSum(0);
                this.cartRepository.save(cart);
            }
        }
    }

    public List<Order> getOrdersByUser(String userEmail) {
        User user = this.userRepository.findByEmail(userEmail);
        if (user != null) {
            return this.orderRepository.findByUserOrderByCreatedAtDesc(user);
        }
        return List.of();
    }

    public Order getOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
}