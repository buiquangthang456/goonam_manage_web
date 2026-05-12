package com.goonamvina.goonamvina.model;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameVi;  // Tên dự án tiếng Việt
    private String nameEn;  // Tên dự án tiếng Anh

    private String investorVi;  // Nhà đầu tư tiếng Việt
    private String investorEn;  // Nhà đầu tư tiếng Anh

    // Thay thế completionDate bằng startYear và completionYear
    private String startYear;  // Năm bắt đầu

    private String completionYear;  // Năm hoàn thành

    @Column(length = 1000)
    @Size(max = 1000, message = "Description cannot exceed 500 characters.")
    private String descriptionVi;  // Mô tả dự án tiếng Việt
    private String descriptionEn;  // Mô tả dự án tiếng Anh


    private String locationVi;  // Địa điểm tiếng Việt
    private String locationEn;  // Địa điểm tiếng Anh

    @Column(nullable = true)
    private String imageProject;  // URL hoặc tên file hình ảnh


    @ManyToOne
    @JoinColumn(name = "projectcategory_id")
    private ProjectCategory projectCategory;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
    @CreationTimestamp
    private LocalDateTime createdDate;  // Ngày giờ tạo mới
}
