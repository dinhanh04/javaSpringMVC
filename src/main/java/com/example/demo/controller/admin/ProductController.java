package com.example.demo.controller.admin;

import com.example.demo.domain.Image;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

    public ProductController(ProductService productService, ProductRepository productRepository, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping("/admin/product")
    public String getAdminDashboard(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products1", products);
        System.out.println("check product list: " + products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/createProduct";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") Product product, @RequestParam("imageFile") MultipartFile file) {
        Image image = imageService.handleSaveImage(file);
        if (image != null) {
            product.setImage(image.getUrl());
        }
        if (product.getSold() == 0) {
            product.setSold(0);
        }
        productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }
}
