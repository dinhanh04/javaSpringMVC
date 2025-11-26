package com.example.demo.controller.admin;

import com.example.demo.domain.Image;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createProductPage(Model model, @ModelAttribute("newProduct")
    @Valid Product product, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile file) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            System.out.println(">>>>: " + error.getField() + ", Message: " + error.getDefaultMessage());
        }
        if(bindingResult.hasErrors()){
            return "admin/product/createProduct";
        }

        Image image = imageService.handleSaveImage(file);
        if (image != null) {
            product.setImage(image);
        }
        if (product.getSold() == 0) {
            product.setSold(0);
        }
        productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductById( Model model, @PathVariable long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product2", product);
        return "admin/product/detail";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("id", id);
        model.addAttribute("newProduct", product);
        return "admin/product/updateProduct";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(Model model, @ModelAttribute("newProduct") Product product,
            @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setSold(product.getSold());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setDetailDesc(product.getDetailDesc());
            
            if (file != null && !file.isEmpty()) {
                Image image = imageService.handleSaveImage(file);
                if (image != null) {
                    currentProduct.setImage(image);
                }
            }
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String showDelete(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "admin/product/deleteProduct";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String doDelete(@PathVariable Long id, RedirectAttributes ra) {
        productService.deleteProductById(id);
        ra.addFlashAttribute("success", "Deleted product #" + id);
        return "redirect:/admin/product";
    }

}
