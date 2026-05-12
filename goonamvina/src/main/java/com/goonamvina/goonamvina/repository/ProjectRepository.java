package com.goonamvina.goonamvina.repository;


import com.goonamvina.goonamvina.model.Project;
import com.goonamvina.goonamvina.model.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectCategoryIdOrderByCreatedDateDesc(Long categoryId);

    // Phương thức tìm kiếm
    List<Project> findByNameViContainingOrNameEnContainingOrInvestorViContainingOrInvestorEnContainingOrProjectCategory_NameViContainingOrProjectCategory_NameEnContainingOrderByCreatedDateDesc(
            String nameVi, String nameEn, String investorVi, String investorEn, String projectCategoryNameVi, String projectCategoryNameEn);



}
