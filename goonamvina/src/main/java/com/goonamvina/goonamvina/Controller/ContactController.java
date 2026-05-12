package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.Feedback;
import com.goonamvina.goonamvina.model.FeedbackStatus;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.FeedbackService;
import com.goonamvina.goonamvina.service.PCService;
import com.goonamvina.goonamvina.service.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PCService pcService;
    @Autowired
    private ProjectCategoryService projectCategoryService;
    @GetMapping("/contact")
    public String viewContact(Model model) {
        // Đảm bảo không có hành động nào gửi phản hồi trước khi session được xử lý
        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        // Thêm đối tượng Feedback vào model
        model.addAttribute("feedback", new Feedback());

        // Đảm bảo không có việc commit response trước khi hoàn thành
        return "contact/contact";
    }

    @PostMapping("/contact")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        try {
            feedback.setStatus(FeedbackStatus.NEW);
            feedbackService.saveFeedback(feedback);
        } catch (Exception e) {
            return "redirect:/contact?error";
        }
        return "redirect:/contact?success";
    }
}
