package com.goonamvina.goonamvina.Controller;


import com.goonamvina.goonamvina.model.Feedback;
import com.goonamvina.goonamvina.model.FeedbackStatus;
import com.goonamvina.goonamvina.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TrashAdminController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/trash")
    public String viewTrash(Model model) {
        List<Feedback> trashFeedbacks = feedbackService.getFeedbacksByStatus(FeedbackStatus.SPAM);
        model.addAttribute("feedbacks", trashFeedbacks);

        return "admin/reply/trash";
    }
    @PostMapping("/trash/restore/{id}")
    @ResponseBody
    public ResponseEntity<?> restoreFeedback(@PathVariable("id") Long id) {
        try {
            Feedback feedback = feedbackService.findById(id);
            if (feedback != null) {
                feedback.setStatus(FeedbackStatus.NEW);  // Khôi phục trạng thái
                feedbackService.saveFeedback(feedback);
                return ResponseEntity.ok(Collections.singletonMap("success", true));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while restoring feedback.");
        }
    }
    @DeleteMapping("/trash/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") Long id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting feedback.");
        }
    }
}
