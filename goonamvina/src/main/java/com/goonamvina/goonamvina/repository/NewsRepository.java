package com.goonamvina.goonamvina.repository;

import com.goonamvina.goonamvina.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByIdNot(Long id);
    List<News> findByTitleViContainingOrTitleEnContainingOrShortContentViContainingOrShortContentEnContaining(
            String titleVi, String titleEn, String shortContentVi, String shortContentEn);
    Optional<News> findBySlug(String slug);
}
