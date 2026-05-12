package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.repository.PCRepository;
import com.goonamvina.goonamvina.repository.ProjectCategoryRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCategoryService {
    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    // Lấy tất cả ProjectCategory
    public List<ProjectCategory> getAllProjectCategory() {
        return projectCategoryRepository.findAll();
    }
    // Lấy tất cả ProjectCategory theo ngôn ngữ
    public List<ProjectCategory> getAllProjectCategory(String lang) {
        if ("vi".equals(lang)) {
            return projectCategoryRepository.findAllVi(); // Implement findAllVi() in the repository
        } else {
            return projectCategoryRepository.findAllEn(); // Implement findAllEn() in the repository
        }
    }

    // Lưu ProjectCategory, tạo slug nếu chưa có
    public void saveProjectCategory(ProjectCategory projectCategory) {
        if (projectCategory.getSlug() == null || projectCategory.getSlug().isEmpty()) {
            projectCategory.setSlug(SlugUtil.toSlug(projectCategory.getNameVi()));
            projectCategory.setSlug(SlugUtil.toSlug(projectCategory.getNameEn()));
        }
        projectCategoryRepository.save(projectCategory);
    }

    // Lấy ProjectCategory theo ID
    public ProjectCategory getProjectCategoryById(Long id) {
        return projectCategoryRepository.findById(id).orElse(null);
    }

    // Lấy ProjectCategory theo slug
    public ProjectCategory getProjectCategoryBySlug(String slug) {
        return projectCategoryRepository.findBySlug(slug).orElse(null);
    }

    // Xóa ProjectCategory theo ID
    public void deleteProjectCategory(Long id) {
        projectCategoryRepository.deleteById(id);
    }
}
