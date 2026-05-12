package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.Metal;
import com.goonamvina.goonamvina.model.Project;
import com.goonamvina.goonamvina.repository.MetalRepository;
import com.goonamvina.goonamvina.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class MetalService {
    @Autowired
    private MetalCategoryService metalCategoryService;
    @Autowired
    private MetalRepository metalRepository;

    public List<Metal> getAllMetal() {
        return metalRepository.findAll();
    }

    public void saveMetal(Metal metal) {
        metalRepository.save(metal);
    }

    public Metal getMetalById(Long id) {
        return metalRepository.findById(id).orElse(null);
    }
    public List<Metal> getMetalByCategoryId(Long categoryId) {
        return metalRepository.findByMetalCategoryId(categoryId);
    }
    public void deleteMetal(Long id) {
        metalRepository.deleteById(id);
    }
    // Hàm tìm kiếm hỗ trợ đa ngôn ngữ
    public List<Metal> searchMetal(String keyword, Locale locale) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return metalRepository.findAll();
        }

        // Xác định ngôn ngữ
        String language = locale.getLanguage();
        if ("vi".equals(language)) {
            // Tìm kiếm theo tiếng Việt
            return metalRepository.findByNameViContainingOrDescriptionViContainingOrNameEnContainingOrDescriptionEnContaining(keyword, "", keyword, "");
        } else {
            // Tìm kiếm theo tiếng Anh
            return metalRepository.findByNameViContainingOrDescriptionViContainingOrNameEnContainingOrDescriptionEnContaining("", keyword, "", keyword);
        }
    }
}
