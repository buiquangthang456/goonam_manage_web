package com.goonamvina.goonamvina.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("error")
    public String viewError(){
        return "error";
    }

}
