package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductDAOImp implements ProductDAO {

    private static final Logger logger = Logger.getLogger(ProductDAOImp.class.getName());

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Product> query = currentSession.createQuery("from Product", Product.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.severe("Error retrieving all products: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            return currentSession.get(Product.class, id);
        } catch (Exception e) {
            logger.severe("Error retrieving product with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void saveProduct(Product product) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            currentSession.persist(product);
        } catch (PersistenceException e) {
            logger.severe("Error saving product: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            currentSession.merge(product);
        } catch (Exception e) {
            logger.severe("Error updating product with ID " + product.getId() + ": " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query query = currentSession.createQuery("delete from Product where id=:productId");
            query.setParameter("productId", id);
            query.executeUpdate();
        } catch (Exception e) {
            logger.severe("Error deleting product with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}