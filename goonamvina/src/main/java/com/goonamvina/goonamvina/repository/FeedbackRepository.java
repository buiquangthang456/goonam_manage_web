package com.goonamvina.goonamvina.repository;

import com.goonamvina.goonamvina.model.Feedback;
import com.goonamvina.goonamvina.model.FeedbackStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Phương thức truy vấn để tìm kiếm Feedback theo status
    List<Feedback> findByStatus(FeedbackStatus status);
}
