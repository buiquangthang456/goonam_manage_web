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
@Table(name = "metals")
public class Metal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVi;
    private String nameEn;
    private String descriptionVi;
    private String descriptionEn;
    @Column(nullable = true)
    private String imageM;  // URL hoặc tên file hình ảnh
    @ManyToOne
    @JoinColumn(name = "metalcategory_id")
    private MetalCategory metalCategory;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
