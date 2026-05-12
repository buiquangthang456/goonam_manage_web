package com.goonamvina.goonamvina;

import com.goonamvina.goonamvina.model.MetalCategory;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;

@Controller
public class trangchu {

    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PCService pcService;

    @Autowired
    private PSCService pscService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MetalCategoryService metalCategoryService;

    @GetMapping("/")
    public String hello(Model model, HttpServletRequest request) {
        String lang = RequestContextUtils.getLocale(request).getLanguage();
        model.addAttribute("lang", lang);

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        List<MetalCategory> metalCategoryList = metalCategoryService.getAllMetalCategory();
        model.addAttribute("metalCategoryList", metalCategoryList);

        return "trangchu";
    }
}
