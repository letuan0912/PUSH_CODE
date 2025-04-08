package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Student;
import com.springboot.dev_spring_boot_demo.entity.User;
import com.springboot.dev_spring_boot_demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/system/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-user")
    public String listUser(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "system/users/list-user";
    }

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "system/users/add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, 
                          @RequestParam(required = false) List<String> roles, 
                          Model model) {
        // Kiểm tra validation theo annotation
        if (bindingResult.hasErrors()) {
            return "system/users/add-user";
        }
        
        // Thêm validate trường hợp username rỗng
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            model.addAttribute("usernameError", "Username không được để trống");
            return "system/users/add-user";
        }
        
        // Kiểm tra password
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            model.addAttribute("passwordError", "Vui lòng điền vào trường này");
            return "system/users/add-user";
        }
        
        // Check if username already exists
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Username đã tồn tại. Vui lòng chọn username khác.");
            return "system/users/add-user";
        }
        
        if (roles == null || roles.isEmpty()) {
            roles = List.of("STUDENT");
        }
        
        try {
            userService.save(user, roles);
            return "redirect:/system/users/list-user";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu người dùng: " + e.getMessage());
            return "system/users/add-user";
        }
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult bindingResult, 
                       @RequestParam List<String> roles, 
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "system/users/add-user";
        }
        
        try {
            userService.save(user, roles);
            return "redirect:/system/users/list-user";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving user: " + e.getMessage());
            return "system/users/add-user";
        }
    }

    @GetMapping("/update-user/{username}")
    public String showUpdateUserForm(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "system/users/update-user";
    }

    @PostMapping("/update-user")
    public String updateUser(@Valid @ModelAttribute User user, BindingResult bindingResult, 
                             @RequestParam(required = false) List<String> roles, 
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "system/users/update-user";
        }
        
        if (roles == null || roles.isEmpty()) {
            roles = List.of("STUDENT");
        }
        
        try {
            userService.save(user, roles);
            return "redirect:/system/users/list-user";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating user: " + e.getMessage());
            return "system/users/update-user";
        }
    }

    @GetMapping("/delete/{username}")
    public String deleteUserAndRedirect(@PathVariable String username, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(username);
            redirectAttributes.addFlashAttribute("successMessage", "User đã được xóa thành công!");
            return "redirect:/system/users/list-user";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa user: " + e.getMessage());
            return "redirect:/system/users/list-user";
        }
    }
}
