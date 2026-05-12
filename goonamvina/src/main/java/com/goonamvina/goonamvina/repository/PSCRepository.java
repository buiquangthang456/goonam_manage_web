package com.goonamvina.goonamvina.repository;


import com.goonamvina.goonamvina.model.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PSCRepository extends JpaRepository<ProductSubCategory,Long> {
    List<ProductSubCategory> findByProductCategoryId(Long Id);
    // Tìm ProductSubCategory theo slug
    Optional<ProductSubCategory> findBySlug(String slug);

    // Lấy danh sách ProductSubCategory theo tiếng Việt
    @Query( value ="SELECT * FROM ProductSubCategory WHERE name_en IS NOT NULL", nativeQuery = true)
    List<ProductSubCategory> findAllVi();

    // Lấy danh sách ProductSubCategory theo tiếng Anh
    @Query( value ="SELECT * FROM ProductSubCategory WHERE name_vi IS NOT NULL", nativeQuery = true)
    List<ProductSubCategory> findAllEn();
}
