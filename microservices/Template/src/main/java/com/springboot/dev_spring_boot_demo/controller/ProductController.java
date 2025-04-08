package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;

    @Value("${upload.path}")
    private String uploadDir;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-product")
    public String list(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products/list-product";
    }

    @GetMapping("/add-product")
    public String formInsert(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/products/add-product";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Product product, BindingResult bindingResult, 
                       @RequestParam("imageFile") MultipartFile imageFile,
                       Model model) {
        
        // If there are validation errors, return to the form
        if (bindingResult.hasErrors()) {
            return "admin/products/add-product";
        }
        
        if (!imageFile.isEmpty()) {
            try {
                // Tạo tên file unique
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + fileExtension;

                // Tạo thư mục nếu chưa tồn tại
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu file
                Path filePath = uploadPath.resolve(newFilename);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn vào database
                product.setImage("/default/images/" + newFilename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Không thể tải lên hình ảnh: " + e.getMessage());
                return "admin/products/add-product";
            }
        }

        productService.saveProduct(product);
        return "redirect:/admin/products/list-product";
    }

    @GetMapping("/edit-product")
    public String formUpdate(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/products/edit-product";
    }

    @PostMapping("/edit-product")
    public String update(@Valid @ModelAttribute Product product, BindingResult bindingResult,
                        @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                        Model model) {
        
        // If there are validation errors, return to the form
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "admin/products/edit-product";
        }
        
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                // Tạo tên file unique
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + fileExtension;

                // Tạo thư mục nếu chưa tồn tại
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu file
                Path filePath = uploadPath.resolve(newFilename);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn vào database
                product.setImage("/default/images/" + newFilename);
            }
            
            productService.updateProduct(product);
            return "redirect:/admin/products/list-product";
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật sản phẩm: " + e.getMessage());
            model.addAttribute("product", product);
            return "admin/products/edit-product";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products/list-product";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product-detail";
    }
}