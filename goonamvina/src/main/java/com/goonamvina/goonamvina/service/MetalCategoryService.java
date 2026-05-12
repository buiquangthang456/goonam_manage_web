package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.MetalCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.repository.MetalCategoryRepository;
import com.goonamvina.goonamvina.repository.ProjectCategoryRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetalCategoryService {
    @Autowired
    private MetalCategoryRepository metalCategoryRepository;

    // Lấy tất cả MetalCategory
    public List<MetalCategory> getAllMetalCategory() {
        return metalCategoryRepository.findAll();
    }

    // Lưu MetalCategory, tạo slug nếu chưa có
    public void saveMetalCategory(MetalCategory metalCategory) {
        if (metalCategory.getSlug() == null || metalCategory.getSlug().isEmpty()) {
            metalCategory.setSlug(SlugUtil.toSlug(metalCategory.getNameVi()));
            metalCategory.setSlug(SlugUtil.toSlug(metalCategory.getNameEn()));
        }
        metalCategoryRepository.save(metalCategory);
    }

    // Lấy MetalCategory theo ID
    public MetalCategory getMetalCategoryById(Long id) {
        return metalCategoryRepository.findById(id).orElse(null);
    }

    // Lấy MetalCategory theo slug
    public MetalCategory getMetalCategoryBySlug(String slug) {
        return metalCategoryRepository.findBySlug(slug).orElse(null);
    }

    // Xóa MetalCategory theo ID
    public void deleteMetalCategory(Long id) {
        metalCategoryRepository.deleteById(id);
    }
}
