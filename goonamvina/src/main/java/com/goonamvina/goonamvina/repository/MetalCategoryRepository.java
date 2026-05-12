package com.goonamvina.goonamvina.repository;

import com.goonamvina.goonamvina.model.MetalCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetalCategoryRepository extends JpaRepository<MetalCategory, Long> {
    // Tìm ProductCategory theo slug
    Optional<MetalCategory> findBySlug(String slug);
}
