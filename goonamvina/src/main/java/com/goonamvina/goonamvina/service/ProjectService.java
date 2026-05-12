package com.goonamvina.goonamvina.service;


import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.model.Project;

import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ProjectService {

    @Autowired
    private ProjectCategoryService projectCategoryService;
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }


    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
    public List<Project> getProjectsByCategoryId(Long categoryId) {
        return projectRepository.findByProjectCategoryIdOrderByCreatedDateDesc(categoryId);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> searchProjects(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return projectRepository.findAll(); // Trả về tất cả nếu không có từ khóa
        }
        return projectRepository.findByNameViContainingOrNameEnContainingOrInvestorViContainingOrInvestorEnContainingOrProjectCategory_NameViContainingOrProjectCategory_NameEnContainingOrderByCreatedDateDesc(
                keyword, keyword, keyword, keyword,keyword,keyword);
    }



}
