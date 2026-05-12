package com.goonamvina.goonamvina.repository;
import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductSubCategory(ProductSubCategory productSubCategory);


// Tìm kiếm theo tên, mô tả và danh mục sản phẩm (theo nameVi và nameEn của ProductSubCategory)
List<Product> findByNameViContainingOrNameEnContainingOrDescriptionViContainingOrDescriptionEnContaining(
        String nameVi, String nameEn, String descriptionVi, String descriptionEn);  // Tìm sản phẩm theo slug của ProductSubCategory
    Optional<Product> findByProductSubCategory_Slug(String slug);


    // Tìm kiếm sản phẩm theo danh mục phụ và loại hoàn thiện bằng tiếng Việt
    List<Product> findByProductSubCategoryAndFinishTypeVi(ProductSubCategory subCategory, String finishTypeVi);

    // Tìm kiếm sản phẩm theo danh mục phụ và loại hoàn thiện bằng tiếng Anh
    List<Product> findByProductSubCategoryAndFinishTypeEn(ProductSubCategory subCategory, String finishTypeEn);

}
