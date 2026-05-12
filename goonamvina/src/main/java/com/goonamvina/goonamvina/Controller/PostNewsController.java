package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.News;
import com.goonamvina.goonamvina.service.MediaStorageService;
import com.goonamvina.goonamvina.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class PostNewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private MediaStorageService mediaStorageService;
    @GetMapping("/post-news")
    public String viewPostNews(Model model){


        model.addAttribute("news", new News());
        model.addAttribute("newsList", newsService.getAllNews()); // Đổi tên thuộc tính này để tránh trùng lặp
        return "admin/postnews/post-news";
    }
    @PostMapping("post-news")
    public String saveNews(@ModelAttribute("news") News news,
                           @RequestParam("news-image") MultipartFile file,
                           Model model) {
        if (!file.isEmpty()) {
            // Sử dụng MediaStorageService để upload file và lưu tên file
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                news.setImageName(fileName); // Lưu tên file vào cơ sở dữ liệu
            }
        }

        newsService.saveNews(news);
        return "redirect:/admin/post-news";
    }
    @GetMapping("/edit-news/{id}")
    public String editNewsForm(@PathVariable("id") Long id, Model model) {
        News existingNews = newsService.getNewsById(id);
        if (existingNews == null) {
            return "redirect:/admin/post-news"; // Điều hướng nếu bài viết không tồn tại
        }
        model.addAttribute("news", existingNews);
        return "admin/postnews/post-news"; // Trang hiển thị form chỉnh sửa
    }
    @PostMapping("/update-news/{id}")
    public String updateNews(@PathVariable("id") Long id,
                             @ModelAttribute("news") News updatedNews,
                             @RequestParam("news-image") MultipartFile file,
                             Model model) {
        News existingNews = newsService.getNewsById(id);
        if (existingNews != null) {
            if (updatedNews.getShortContentVi().length() > 1000) {
                model.addAttribute("error", "Short content in Vietnamese cannot exceed 1000 characters");
                return "admin/postnews/post-news"; // Trả về trang với thông báo lỗi
            }
            if (updatedNews.getShortContentEn().length() > 1000) {
                model.addAttribute("error", "Short content in English cannot exceed 1000 characters");
                return "admin/postnews/post-news"; // Trả về trang với thông báo lỗi
            }

            existingNews.setTitleVi(updatedNews.getTitleVi());
            existingNews.setTitleEn(updatedNews.getTitleEn());
            existingNews.setContentVi(updatedNews.getContentVi());
            existingNews.setContentEn(updatedNews.getContentEn());
            existingNews.setShortContentVi(updatedNews.getShortContentVi());
            existingNews.setShortContentEn(updatedNews.getShortContentEn());

            // Kiểm tra xem có file mới được upload hay không
            if (!file.isEmpty()) {
                String fileName = mediaStorageService.uploadFile(file);
                if (fileName != null) {
                    existingNews.setImageName(fileName); // Cập nhật tên file mới
                }
            }

            newsService.saveNews(existingNews);
        }

        return "redirect:/admin/post-news";
    }
    @GetMapping("/delete-news/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        // Xóa sản phẩm từ bảng products
        newsService.deleteNews(id);
        return "redirect:/admin/post-news";
    }

    @PostMapping("/upload-image")
    @ResponseBody
    public String uploadImageFromEditor(@RequestBody String base64Image) {
        String fileUrl = "";
        if (base64Image.startsWith("data:image")) {
            String[] parts = base64Image.split(",");
            String imageString = parts[1];
            byte[] imageBytes = Base64.getDecoder().decode(imageString);

            String fileName = UUID.randomUUID().toString() + ".png";
            String uploadDir = System.getProperty("user.dir") + "/uploads/images/";

            try {
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File savedFile = new File(uploadDir + fileName);
                try (FileOutputStream fos = new FileOutputStream(savedFile)) {
                    fos.write(imageBytes);
                }
                fileUrl = "/uploads/images/" + fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileUrl; // Trả về đường dẫn của file ảnh sau khi lưu
    }
    @GetMapping("/search-postnews")
    public String searchNews(@RequestParam("keyword") String keyword, Model model) {
        List<News> newsList = newsService.searchNews(keyword);
        model.addAttribute("newsList", newsList);
        model.addAttribute("keyword", keyword);
        return "admin/postnews/post-news";
    }
}
