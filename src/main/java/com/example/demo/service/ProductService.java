package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public void handleSaveProduct(Product product) {
        Product saveProduct = this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public void addProductToCart(String email, Long productId) {
        System.out.println(">>> ProductService.addProductToCart - Email: " + email + ", ProductId: " + productId);

        // Get user by email
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            System.out.println(">>> ERROR: User not found with email: " + email);
            throw new RuntimeException("User not found with email: " + email);
        }

        Long userId = user.getId();
        System.out.println(">>> User found - UserId: " + userId);

        // Get or create cart
        Cart cart = this.cartRepository.findByUserId(userId);
        if (cart == null) {
            System.out.println(">>> Creating new cart for user: " + userId);
            Cart otherCart = new Cart();
            otherCart.setUserId(userId);
            otherCart.setSum(0);
            cart = this.cartRepository.save(otherCart);
        }

        // Get product
        Optional<Product> productOpt = this.productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            System.out.println(">>> Product found: " + product.getName());

            // Check if product already exists in cart
            System.out.println(
                    ">>> Checking if product exists - CartId: " + cart.getId() + ", ProductId: " + product.getId());
            CartDetail existingCartDetail = this.cartDetailRepository.findByCartIdAndProductId(cart.getId(),
                    product.getId());
            System.out.println(">>> Existing cart detail: "
                    + (existingCartDetail != null ? "FOUND (ID: " + existingCartDetail.getId() + ")" : "NOT FOUND"));

            if (existingCartDetail != null) {
                // Product already in cart - increase quantity
                System.out.println(">>> Product already in cart - updating quantity from "
                        + existingCartDetail.getQuantity() + " to " + (existingCartDetail.getQuantity() + 1));
                existingCartDetail.setQuantity(existingCartDetail.getQuantity() + 1);
                this.cartDetailRepository.save(existingCartDetail);
                System.out.println(">>> Updated quantity to: " + existingCartDetail.getQuantity());
            } else {
                // Product not in cart - create new cart detail
                System.out.println(">>> Adding new product to cart");
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCartId(cart.getId());
                cartDetail.setProductId(product.getId());
                cartDetail.setQuantity(1);
                cartDetail.setPrice(product.getPrice());
                this.cartDetailRepository.save(cartDetail);

                // Update cart sum (only for new items)
                cart.setSum(cart.getSum() + 1);
                this.cartRepository.save(cart);
            }

            System.out.println(">>> Cart updated - Total unique items: " + cart.getSum());
        } else {
            System.out.println(">>> ERROR: Product not found with id: " + productId);
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    public List<com.example.demo.dto.CartItemDTO> getCartItems(String email) {
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            return new java.util.ArrayList<>();
        }

        Cart cart = this.cartRepository.findByUserId(user.getId());
        if (cart == null) {
            return new java.util.ArrayList<>();
        }

        List<CartDetail> cartDetails = this.cartDetailRepository.findByCartId(cart.getId());
        List<com.example.demo.dto.CartItemDTO> cartItems = new java.util.ArrayList<>();

        for (CartDetail detail : cartDetails) {
            Product product = this.productRepository.findById(detail.getProductId().longValue());
            if (product != null) {
                com.example.demo.dto.CartItemDTO item = new com.example.demo.dto.CartItemDTO();
                item.setCartDetailId(detail.getId());
                item.setProductId(product.getId());
                item.setProductName(product.getName());
                item.setProductImage(product.getImage() != null ? product.getImage().getUrl() : "");
                item.setPrice(detail.getPrice());
                item.setQuantity(detail.getQuantity());
                item.setTotal(detail.getPrice() * detail.getQuantity());
                cartItems.add(item);
            }
        }

        return cartItems;
    }

    public Integer getCartSum(String email) {
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            return 0;
        }

        Cart cart = this.cartRepository.findByUserId(user.getId());
        if (cart == null) {
            return 0;
        }

        return cart.getSum();
    }
}
