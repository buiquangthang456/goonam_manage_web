package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.Feedback;
import com.goonamvina.goonamvina.model.FeedbackStatus;
import com.goonamvina.goonamvina.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    public List<Feedback> getFeedbacksByStatus(FeedbackStatus status) {
        return feedbackRepository.findByStatus(status);
    }

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }
}
