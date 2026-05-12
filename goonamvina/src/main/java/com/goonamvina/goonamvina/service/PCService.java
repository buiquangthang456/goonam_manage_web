package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.repository.PCRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PCService {
    @Autowired
    private PCRepository pcRepository;

    public List<ProductCategory> getAllPC() {
        return pcRepository.findAll();
    }
    public List<ProductCategory> getAllPC(String lang) {
        if ("vi".equals(lang)) {
            return pcRepository.findAllVi();
        } else {
            return pcRepository.findAllEn();
        }
    }
    // Lưu ProductCategory, tạo slug nếu chưa có
    public void savePC(ProductCategory pc) {
        if (pc.getSlug() == null || pc.getSlug().isEmpty()) {
            pc.setSlug(SlugUtil.toSlug(pc.getNameVi()));
            pc.setSlug(SlugUtil.toSlug(pc.getNameEn()));
        }
        pcRepository.save(pc);
    }

    public ProductCategory getPCById(Long id) {
        return pcRepository.findById(id).orElse(null);
    }
    // Lấy ProductCategory theo slug
    public ProductCategory getProductCategoryBySlug(String slug) {
        return pcRepository.findBySlug(slug).orElse(null);
    }
    public void deletePC(Long id) {
        pcRepository.deleteById(id);
    }
}
