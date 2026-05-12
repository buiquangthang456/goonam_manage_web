package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.*;
import com.goonamvina.goonamvina.service.MediaStorageService;
import com.goonamvina.goonamvina.service.ProjectCategoryService;
import com.goonamvina.goonamvina.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectAdminController {
    @Autowired
    ProjectCategoryService projectCategoryService;
    @Autowired
    ProjectService projectService;
    @Autowired
    private MediaStorageService mediaStorageService;

    @GetMapping("/project-category")
    public String viewProjectCategory(Model model){
        model.addAttribute("project_category", new ProjectCategory());
        model.addAttribute("project_categories", projectCategoryService.getAllProjectCategory());
        return "admin/project/projects-category";
    }

    @PostMapping("/project-category")
    public String saveProjectCategory(@ModelAttribute("project_category") ProjectCategory projectCategory,
                                      @RequestParam("project-image") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                projectCategory.setImageProjectCategory(fileName);
            }
        }

        projectCategoryService.saveProjectCategory(projectCategory);
        return "redirect:/admin/project-category";
    }

    @GetMapping("/edit-projectcategory/{id}")
    public String editProjectCategoryForm(@PathVariable("id") Long id, Model model) {
        ProjectCategory existingProjectCategory = projectCategoryService.getProjectCategoryById(id);
        if (existingProjectCategory != null) {
            model.addAttribute("project_category", existingProjectCategory);
            return "admin/project/projects-category";
        } else {
            return "redirect:/admin/project-category";
        }
    }

    @PostMapping("/update-projectcategory/{id}")
    public String updateProjectCategory(@PathVariable("id") Long id,
                                        @ModelAttribute("project_category") ProjectCategory updatedProjectCategory,
                                        @RequestParam("project-image") MultipartFile file) {
        ProjectCategory existingProjectCategory = projectCategoryService.getProjectCategoryById(id);

        if (existingProjectCategory != null) {
            existingProjectCategory.setNameVi(updatedProjectCategory.getNameVi());
            existingProjectCategory.setNameEn(updatedProjectCategory.getNameEn());
            if (!file.isEmpty()) {
                String fileName = mediaStorageService.uploadFile(file);
                if (fileName != null) {
                    existingProjectCategory.setImageProjectCategory(fileName);
                }
            }

            projectCategoryService.saveProjectCategory(existingProjectCategory);
        }

        return "redirect:/admin/project-category";
    }

    @GetMapping("/delete-projectcategory/{id}")
    public String deleteProjectCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            projectCategoryService.deleteProjectCategory(id);
            redirectAttributes.addFlashAttribute("message", "Project Category deleted successfully.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete this category. Please delete its Project first.");
        }
        return "redirect:/admin/project-category";
    }

    // Add project

    @GetMapping("/add-project")
    public String viewProjectAdd(@RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "startYear", required = false) Integer startYear,
                                 @RequestParam(value = "completionYear", required = false) Integer completionYear,
                                 Model model){
        List<Project> projects;
        if (keyword == null || keyword.trim().isEmpty()) {
            projects = projectService.getAllProject();
        } else {
            projects = projectService.searchProjects(keyword);
        }
        model.addAttribute("projects", projects);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startYear", startYear);
        model.addAttribute("completionYear", completionYear);
        model.addAttribute("project", new Project());
        model.addAttribute("project_categories", projectCategoryService.getAllProjectCategory());
        return "admin/project/add-projects";
    }

    @PostMapping("/add-project")
    public String saveProject(@ModelAttribute("project") Project project,
                              @RequestParam("project-image") MultipartFile file,
                              BindingResult result,
                              Model model) {
        if (project.getDescriptionVi().length() > 1000) {
            result.rejectValue("description", "error.project", "Description cannot exceed 1000 characters.");
        }

        if (result.hasErrors()) {
            model.addAttribute("project", project);
            model.addAttribute("project_categories", projectCategoryService.getAllProjectCategory());
            return "admin/project/add-projects";
        }

        if (!file.isEmpty()) {
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                project.setImageProject(fileName);
            }
        }

        projectService.saveProject(project);
        return "redirect:/admin/add-project";
    }

    @GetMapping("/edit-project/{id}")
    public String editProjectForm(@PathVariable("id") Long id, Model model) {
        Project existingProject = projectService.getProjectById(id);
        if (existingProject == null) {
            existingProject = new Project();
        }
        model.addAttribute("project", existingProject);
        model.addAttribute("project_categories", projectCategoryService.getAllProjectCategory());
        return "admin/project/add-projects";
    }

    @PostMapping("/update-project/{id}")
    public String updateProject(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("project") Project updatedProject,
                                @RequestParam("project-image") MultipartFile file,
                                BindingResult result,
                                Model model) {
        if (updatedProject.getDescriptionVi().length() > 1000) {
            result.rejectValue("description", "error.project", "Description cannot exceed 1000 characters.");
        }

        if (result.hasErrors()) {
            model.addAttribute("project", updatedProject);
            model.addAttribute("project_categories", projectCategoryService.getAllProjectCategory());
            return "admin/project/add-projects";
        }

        Project existingProject = projectService.getProjectById(id);
        existingProject.setNameVi(updatedProject.getNameVi());
        existingProject.setNameEn(updatedProject.getNameEn());
        existingProject.setDescriptionVi(updatedProject.getDescriptionVi());
        existingProject.setDescriptionEn(updatedProject.getDescriptionEn());
        existingProject.setInvestorVi(updatedProject.getInvestorVi());
        existingProject.setInvestorEn(updatedProject.getInvestorEn());
        existingProject.setStartYear(updatedProject.getStartYear());
        existingProject.setCompletionYear(updatedProject.getCompletionYear());
        existingProject.setLocationVi(updatedProject.getLocationVi());
        existingProject.setLocationEn(updatedProject.getLocationEn());
        existingProject.setProjectCategory(updatedProject.getProjectCategory());

        if (!file.isEmpty()) {
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                existingProject.setImageProject(fileName);
            }
        }

        projectService.saveProject(existingProject);
        return "redirect:/admin/add-project";
    }

    @GetMapping("/delete-project/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/admin/add-project";
    }

    @GetMapping("/search-projects")
    public String searchProjects(@RequestParam("keyword") String keyword,
                                 @RequestParam(value = "startYear", required = false) Integer startYear,
                                 @RequestParam(value = "completionYear", required = false) Integer completionYear,
                                 Model model) {
        List<Project> projects = projectService.searchProjects(keyword);
        model.addAttribute("projects", projects);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startYear", startYear);
        model.addAttribute("completionYear", completionYear);
        return "admin/project/add-projects";
    }
}
