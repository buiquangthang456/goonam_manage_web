package com.goonamvina.goonamvina.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metals_category")
public class MetalCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVi;   // Tên danh mục tiếng Việt
    private String nameEn;   // Tên danh mục tiếng Anh

    @Column(unique = true)
    private String slug;
    private String descriptionVi;  // Mô tả tiếng Việt
    private String descriptionEn;  // Mô tả tiếng Anh

    @Column(nullable = true)
    private String imageMC;  // URL hoặc tên file hình ảnh

    @OneToMany(mappedBy = "metalCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Metal> metals;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
