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
@Table(name = "sub_products")
public class ProductSubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)  // Đảm bảo tên là duy nhất trong cơ sở dữ liệu
    private String nameVi;  // Tên tiếng Việt
    private String nameEn;  // Tên tiếng Anh
    @Column(unique = true)
    private String slug;
    @Column(nullable = true)
    private String imagePSC;  // URL hoặc tên file hình ảnh

    @ManyToOne
    @JoinColumn(name = "pc_id")
    private ProductCategory productCategory;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
