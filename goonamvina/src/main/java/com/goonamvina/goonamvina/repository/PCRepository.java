package com.goonamvina.goonamvina.repository;
import com.goonamvina.goonamvina.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PCRepository  extends JpaRepository<ProductCategory,Long>{

    // Tìm ProductCategory theo slug
    Optional<ProductCategory> findBySlug(String slug);
    @Query(value = "SELECT * FROM product_categories WHERE name_en IS NOT NULL", nativeQuery = true)
    List<ProductCategory> findAllEn();

    // Native query để trả về tất cả các cột của bảng product_categories cho ngôn ngữ tiếng Việt
    @Query(value = "SELECT * FROM product_categories WHERE name_vi IS NOT NULL", nativeQuery = true)
    List<ProductCategory> findAllVi();
}
