package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.dao.CustomerDAO;
import com.springboot.dev_spring_boot_demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImp(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerDAO.deleteById(id);
    }

    @Override
    public long countCustomers() {
        return customerDAO.count();
    }
}