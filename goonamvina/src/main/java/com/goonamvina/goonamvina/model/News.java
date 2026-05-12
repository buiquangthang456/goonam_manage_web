package com.goonamvina.goonamvina.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleVi;   // Tiêu đề tiếng Việt
    private String titleEn;   // Tiêu đề tiếng Anh
    @Size(max = 1000, message = "Short content cannot exceed 1000 characters")
    private String shortContentVi;  // Nội dung ngắn tiếng Việt
    @Size(max = 1000, message = "Short content cannot exceed 1000 characters")
    private String shortContentEn;  // Nội dung ngắn tiếng Anh
    @Column(columnDefinition = "LONGTEXT")
    private String contentVi;  // Full content in Vietnamese
    @Column(columnDefinition = "LONGTEXT")
    private String contentEn;  // Full content in English
    @Column(unique = true)
    private String slug;
    @Column(nullable = true)
    private String imageName;  // URL hoặc tên file hình ảnh

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
