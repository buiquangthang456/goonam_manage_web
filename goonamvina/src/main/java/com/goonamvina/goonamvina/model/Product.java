package com.goonamvina.goonamvina.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVi;  // Tên sản phẩm tiếng Việt
    private String nameEn;  // Tên sản phẩm tiếng Anh
    @Column(unique = true)
    private String slug;
    @Column(length = 1000)
    private String descriptionVi;  // Mô tả sản phẩm tiếng Việt
    @Column(length = 1000)
    private String descriptionEn;  // Mô tả sản phẩm tiếng Anh

    @Column(nullable = true)
    private String imageP;  // URL hoặc tên file hình ảnh

    @ManyToOne
    @JoinColumn(name = "psc_id")
    private ProductSubCategory productSubCategory;

    private String finishTypeVi; // Loại hoàn thiện cho tiếng Việt
    private String finishTypeEn; // Loại hoàn thiện cho tiếng Anh

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
