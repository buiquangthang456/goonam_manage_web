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
@Table(name = "project_categories")
public class ProjectCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameVi;
    private String nameEn;
    @Column(unique = true)
    private String slug;
    @Column(nullable = true)
    private String imageProjectCategory;  // URL hoặc tên file hình ảnh

    @OneToMany(mappedBy = "projectCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }

}
