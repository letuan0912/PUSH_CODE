package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.dao.ProductDAO;
import com.springboot.dev_spring_boot_demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        if (product.getId() != null) {
            updateProduct(product);
        } else {
            if (product.getStockQuantity() == null) {
                product.setStockQuantity(0);
            }
            productDAO.saveProduct(product);
        }
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (product.getId() != null) {
            Product existingProduct = productDAO.getProductById(product.getId());
            
            // If no new image is provided, keep the existing one
            if (product.getImage() == null || product.getImage().isEmpty()) {
                product.setImage(existingProduct.getImage());
            }
            
            productDAO.updateProduct(product);
        } else {
            throw new RuntimeException("Product ID cannot be null for update operation");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}