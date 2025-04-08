package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    void save(User user, List<String> roles);

    void updateUserRole(String username, String role, boolean hasRole);

    void deleteUser(String username);

    long countUsers();

    boolean existsByUsername(String username);


}