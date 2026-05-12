package com.goonamvina.goonamvina.repository;

import com.goonamvina.goonamvina.model.Metal;
import com.goonamvina.goonamvina.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetalRepository extends JpaRepository<Metal, Long> {
    List<Metal> findByMetalCategoryId(Long categoryId);

    // Tìm kiếm dựa trên từ khóa và ngôn ngữ
    List<Metal> findByNameViContainingOrDescriptionViContainingOrNameEnContainingOrDescriptionEnContaining(String keywordVi, String keywordEn, String keywordVi2, String keywordEn2);
}
