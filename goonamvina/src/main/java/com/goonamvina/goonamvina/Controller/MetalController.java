package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.*;
import com.goonamvina.goonamvina.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/googenmetal")
public class MetalController {
    @Autowired
    private MetalService metalService;
    @Autowired
    private MetalCategoryService metalCategoryService;
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
    @GetMapping("")
    public String viewGoonammetal(Model model , HttpServletRequest request){
        Locale locale = RequestContextUtils.getLocale(request);
        String lang = locale.getLanguage();
        model.addAttribute("lang", lang);
        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        List<MetalCategory> metalCategoryList = metalCategoryService.getAllMetalCategory();
        model.addAttribute("metalCategoryList", metalCategoryList);
        return "googenmetal";
    }
    @GetMapping("/{slug}")
    public String viewMetal(@PathVariable String slug, Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        String lang = locale.getLanguage(); // Lấy ngôn ngữ hiện tại
        model.addAttribute("lang", lang);  // Thêm ngôn ngữ vào model để dùng ở view


        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        MetalCategory metalCategory = metalCategoryService.getMetalCategoryBySlug(slug);


        List<Metal> metalList = metalService.getMetalByCategoryId(metalCategory.getId());

        model.addAttribute("metalCategory", metalCategory);
        model.addAttribute("metalList", metalList);

        List<MetalCategory> metalCategoryList = metalCategoryService.getAllMetalCategory();
        model.addAttribute("metalCategoryList", metalCategoryList);

        return "metal/metal";  // Tên template cho danh sách kim loại trong một danh mục
    }
}
