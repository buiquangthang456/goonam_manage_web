package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.Feedback;
import com.goonamvina.goonamvina.model.FeedbackStatus;
import com.goonamvina.goonamvina.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/reply")
public class FeedbackController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("")
    public String viewReplyDashboard(Model model) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByStatus(FeedbackStatus.NEW);
        model.addAttribute("feedbacks", feedbacks);
        return "admin/reply/reply";
    }
    @PostMapping("/test-email")
    @ResponseBody
    public ResponseEntity<?> testSendEmail(@RequestParam("replyEmail") String email,
                                           @RequestParam("replyMessage") String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Phản hồi từ GoonamVina");
        mailMessage.setText(message);

        try {
            mailSender.send(mailMessage);
            return ResponseEntity.ok("Phản hồi đã được gửi!");
        } catch (MailException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi gửi phản hồi.");
        }
    }
    @PostMapping("/moveToTrash/{id}")
    @ResponseBody
    public ResponseEntity<?> moveToTrash(@PathVariable("id") Long id) {
        try {
            Feedback feedback = feedbackService.findById(id);
            if (feedback != null) {
                feedback.setStatus(FeedbackStatus.SPAM);  // Cập nhật trạng thái thành SPAM
                feedbackService.saveFeedback(feedback);
                return ResponseEntity.ok(Collections.singletonMap("success", true));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while moving feedback to trash.");
        }
    }

}
