package com.goonamvina.goonamvina.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAdminController {
    @GetMapping("/admin/login")
    public String viewLogin(){


        return "admin/login";
    }
}
