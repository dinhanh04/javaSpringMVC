package com.example.demo.controller.client;


import com.example.demo.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CardController {
    @GetMapping("/cart")
    public String getCardPage(Model model) {
//        List<Product> products = this.productService.getAllProducts();
//        model.addAttribute("products", carts);
        return "client/cart/cart";
    }
}
