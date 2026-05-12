package com.goonamvina.goonamvina.repository;


import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory,Long> {
    // Tìm ProjectCategory theo slug
    Optional<ProjectCategory> findBySlug(String slug);

    // Native query cho tiếng Anh
    @Query(value = "SELECT * FROM project_categories WHERE name_en IS NOT NULL", nativeQuery = true)
    List<ProjectCategory> findAllEn();

    // Native query cho tiếng Việt
    @Query(value = "SELECT * FROM project_categories WHERE name_vi IS NOT NULL", nativeQuery = true)
    List<ProjectCategory> findAllVi();
}
