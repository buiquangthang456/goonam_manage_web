package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.PCService;
import com.goonamvina.goonamvina.service.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IntroduceController {
    @Autowired
    private PCService pcService;
    @Autowired
    private ProjectCategoryService projectCategoryService;
    @GetMapping("/history")
    public String history(Model model) {
        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        return "introduce-company/history-company";
    }
    @GetMapping("/collective")
    public String collective(Model model) {
        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);
        return "introduce-company/our-collective";
    }
    @GetMapping("/main-role")
    public String main_role(Model model) {
        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);
        return "introduce-company/main-role";
    }
}
