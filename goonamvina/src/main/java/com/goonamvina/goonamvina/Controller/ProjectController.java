package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.model.Project;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.PCService;
import com.goonamvina.goonamvina.service.ProjectCategoryService;
import com.goonamvina.goonamvina.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private PCService pcService;

    @GetMapping("")
    public String projects_list(Model model){

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);
        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);


        return "projects/projects-list";  // Tên template cho danh mục dự án
    }

    @GetMapping("/detail/{id}")
    public String projectDetail(@PathVariable Long id, Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        String lang = locale.getLanguage();

        Project project = projectService.getProjectById(id);
        if (project == null) {
            return "error/404";
        }

        // Du an khac trong cung danh muc (du an lien quan + nguon anh gallery)
        List<Project> sameCategoryProjects = projectService.getProjectsByCategoryId(
                project.getProjectCategory().getId());

        List<Project> relatedProjects = new ArrayList<>();
        List<String> gallery = new ArrayList<>();
        if (project.getImageProject() != null) gallery.add(project.getImageProject());

        for (Project p : sameCategoryProjects) {
            if (!p.getId().equals(id)) {
                if (relatedProjects.size() < 3) relatedProjects.add(p);
                if (gallery.size() < 6 && p.getImageProject() != null) gallery.add(p.getImageProject());
            }
        }

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();

        model.addAttribute("project", project);
        model.addAttribute("relatedProjects", relatedProjects);
        model.addAttribute("gallery", gallery);
        model.addAttribute("lang", lang);
        model.addAttribute("productCategoryList", productCategoryList);
        model.addAttribute("projectCategoryList", projectCategoryList);

        return "projects/project-detail";
    }

    @GetMapping("/{slug}")
    public String viewProjects(@PathVariable String slug, Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);  // Lấy ngôn ngữ từ LocaleResolver
        String lang = locale.getLanguage();   // Lấy mã ngôn ngữ ('vi' hoặc 'en')   // Lấy mã ngôn ngữ ('vi' hoặc 'en')

// In ra ngôn ngữ hiện tại để kiểm tra
        System.out.println("Ngôn ngữ hiện tại: " + lang);

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        ProjectCategory projectCategory = projectCategoryService.getProjectCategoryBySlug(slug);
        if (projectCategory == null) {
            return "error/404";  // Trang lỗi nếu không tìm thấy danh mục dự án
        }
        List<Project> projectList = projectService.getProjectsByCategoryId(projectCategory.getId());

        model.addAttribute("projectCategory", projectCategory);
        model.addAttribute("projectList", projectList);
        model.addAttribute("lang", lang);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        return "projects/projects";  // Tên template cho danh sách dự án trong một danh mục
    }
}
