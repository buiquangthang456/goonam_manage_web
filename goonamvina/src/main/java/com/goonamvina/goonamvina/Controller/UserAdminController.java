package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.User;
import com.goonamvina.goonamvina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserAdminController {

    private final UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String viewUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/users";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user, Model model) {
        boolean hasErrors = false;
        if (userService.findByUsername(user.getUsername()).isPresent()) {

            model.addAttribute("usernameError", "Username already exists");
            hasErrors = true;
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("emailError", "Email already exists");
            hasErrors = true;
        }

        if (hasErrors) {
            return "admin/user/users";
        }

        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
