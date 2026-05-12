package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.User;
import com.goonamvina.goonamvina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.constraints.Null;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DashboardAdminController {
    @Autowired
    private UserService userService;
    @GetMapping({"","/dashboard"})
    public String dashboard(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername).orElse(null);

        model.addAttribute("user",currentUser);
        return "admin/dashboard";
    }
}
