package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.repository.PSCRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PSCService {
    @Autowired
    private PSCRepository pscRepository;

    // Lấy tất cả các ProductSubCategory
    public List<ProductSubCategory> getAllPSC() {
        return pscRepository.findAll();
    }

    public List<ProductSubCategory> getAllPSC(String lang) {
        if ("vi".equals(lang)) {
            return pscRepository.findAllVi();
        } else {
            return pscRepository.findAllEn();
        }
    }
    // Lưu ProductSubCategory, tạo slug nếu chưa có
    public void savePSC(ProductSubCategory psc) {
        if (psc.getSlug() == null || psc.getSlug().isEmpty()) {
            psc.setSlug(SlugUtil.toSlug(psc.getNameVi()));
            psc.setSlug(SlugUtil.toSlug(psc.getNameEn()));
        }
        pscRepository.save(psc);
    }

    // Lấy ProductSubCategory theo ID
    public ProductSubCategory getPSCById(Long id) {
        return pscRepository.findById(id).orElse(null);
    }

    // Lấy ProductSubCategory theo slug
    public ProductSubCategory getPSCBySlug(String slug) {
        return pscRepository.findBySlug(slug).orElse(null);
    }


    // Lấy danh sách SubProductCategory theo ProductCategory ID
    public List<ProductSubCategory> getSubProductCategoriesByPCId(Long pcId) {
        return pscRepository.findByProductCategoryId(pcId);
    }

    public void deletePSC(Long id) {
        pscRepository.deleteById(id);
    }
}
