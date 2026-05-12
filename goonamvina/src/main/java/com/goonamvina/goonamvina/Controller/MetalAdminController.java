package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.*;
import com.goonamvina.goonamvina.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class MetalAdminController {

    @Autowired
    private MetalCategoryService metalCategoryService;
    @Autowired
    private MetalService metalService;

    @GetMapping("/metal-category")
    public String viewMetalCategory(Model model){


        model.addAttribute("metal_category", new MetalCategory());
        model.addAttribute("metal_categories", metalCategoryService.getAllMetalCategory());
        return "admin/metal/metal-category";
    }

    @PostMapping("/metal-category")
    public String saveMetalCategory(@ModelAttribute("metal_category") MetalCategory metalCategory,
                                    @RequestParam("metal-image") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") + "/uploads/images/";
            try {
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File savedFile = new File(uploadDir + fileName);
                file.transferTo(savedFile);
                // Lưu tên file vào cơ sở dữ liệu
                metalCategory.setImageMC(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        metalCategoryService.saveMetalCategory(metalCategory);
        return "redirect:/admin/metal-category";
    }
    @GetMapping("/edit-metalcategory/{id}")
    public String editMetalCategoryForm(@PathVariable("id") Long id, Model model) {
        MetalCategory existingMetalCateogory = metalCategoryService.getMetalCategoryById(id);
        if (existingMetalCateogory != null) {
            model.addAttribute("metal_category", existingMetalCateogory);
            return "admin/metal/metal-category"; // Trang hiển thị form chỉnh sửa
        } else {
            return "redirect:/admin/metal-category"; // Điều hướng nếu bài viết không tồn tại
        }
    }

    @PostMapping("/update-metalcategory/{id}")
    public String updateMetalCategory(@PathVariable("id") Long id,
                                      @ModelAttribute("metal_category") MetalCategory updatedMetalCategory,
                                      @RequestParam("metal-image") MultipartFile file) {
        MetalCategory existingMetalCategory = metalCategoryService.getMetalCategoryById(id);
        if (existingMetalCategory != null) {
            existingMetalCategory.setNameVi(updatedMetalCategory.getNameVi());
            existingMetalCategory.setNameEn(updatedMetalCategory.getNameEn());
            existingMetalCategory.setDescriptionVi(updatedMetalCategory.getDescriptionVi());
            existingMetalCategory.setDescriptionEn(updatedMetalCategory.getDescriptionEn());

            // Kiểm tra xem có file mới được upload hay không
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/images/";

                try {
                    File directory = new File(uploadDir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    File savedFile = new File(uploadDir + fileName);
                    file.transferTo(savedFile);

                    // Cập nhật tên file mới vào cơ sở dữ liệu
                    existingMetalCategory.setImageMC(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Lưu thông tin bài viết đã cập nhật vào cơ sở dữ liệu
            metalCategoryService.saveMetalCategory(existingMetalCategory);
        }

        return "redirect:/admin/metal-category";
    }

    @GetMapping("/delete-metalcategory/{id}")
    public String deleteMetalCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            // Xóa sản phẩm từ bảng products category
           metalCategoryService.deleteMetalCategory(id);
            redirectAttributes.addFlashAttribute("message", "Metal Category deleted successfully.");
        } catch (DataIntegrityViolationException e) {
            // Thông báo lỗi khi không thể xóa do có liên kết với sản phẩm con
            redirectAttributes.addFlashAttribute("error", "Cannot delete this category. Please delete its Metal first.");
        }
        return "redirect:/admin/metal-category";
    }

    //Add metalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll


    @GetMapping("/add-metal")
    public String viewAddMetal(Model model){

        model.addAttribute("metal",new Metal());
        model.addAttribute("metals",metalService.getAllMetal());
        model.addAttribute("metal_categories",metalCategoryService.getAllMetalCategory());
        return "admin/metal/add-metal";
    }
    @PostMapping("add-metal")
    public String saveMetal(@ModelAttribute("metal") Metal metal,
                              @RequestParam("metal-image") MultipartFile file,
                              Model model  ){
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            // Lấy đường dẫn tuyệt đối của thư mục gốc dự án
            String uploadDir = System.getProperty("user.dir") + "/uploads/images/";
            try {
                // Tạo thư mục nếu chưa tồn tại
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();  // Tạo thư mục và các thư mục cha nếu cần thiết
                }
                // Lưu file vào thư mục
                File savedFile = new File(uploadDir + fileName);
                file.transferTo(savedFile);
                // Lưu tên file vào cơ sở dữ liệu
                metal.setImageM(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        metalService.saveMetal(metal);
        return "redirect:/admin/add-metal";
    }
    @GetMapping("/edit-metal/{id}")
    public String editMetalForm(@PathVariable("id") Long id, Model model) {
        Metal existingMetal = metalService.getMetalById(id);
        List<MetalCategory> metalCategories = metalCategoryService.getAllMetalCategory();

        if (existingMetal != null) {
            model.addAttribute("metal",existingMetal);
            model.addAttribute("metal_categories", metalCategories);

            return "admin/metal/add-metal"; // Trang hiển thị form chỉnh sửa
        } else {
            return "redirect:/admin/add-metal"; // Điều hướng nếu bài viết không tồn tại
        }
    }
    @PostMapping("/update-metal/{id}")
    public String updateMetal(@PathVariable("id") Long id,
                                @ModelAttribute("metal") Metal updatedMetal,
                                @RequestParam("metal-image") MultipartFile file) {
        Metal existingMetal = metalService.getMetalById(id);

        if (existingMetal != null) {
            existingMetal.setNameVi(updatedMetal.getNameVi());
            existingMetal.setNameEn(updatedMetal.getNameEn());
            existingMetal.setDescriptionVi(updatedMetal.getDescriptionVi());
            existingMetal.setDescriptionEn(updatedMetal.getDescriptionEn());
            existingMetal.setMetalCategory(updatedMetal.getMetalCategory());
            // Kiểm tra xem có file mới được upload hay không
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/images/";

                try {
                    File directory = new File(uploadDir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    File savedFile = new File(uploadDir + fileName);
                    file.transferTo(savedFile);

                    // Cập nhật tên file mới vào cơ sở dữ liệu
                    existingMetal.setImageM(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Lưu thông tin bài viết đã cập nhật vào cơ sở dữ liệu
            metalService.saveMetal(existingMetal);
        }

        return "redirect:/admin/add-metal";
    }
    @GetMapping("/delete-metal/{id}")
    public String deleteMetal(@PathVariable("id") Long id) {
        // Xóa sản phẩm từ bảng products category
        metalService.deleteMetal(id);
        return "redirect:/admin/add-metal";
    }
    @GetMapping("/search-metal")
    public String searchMetals(@RequestParam("keyword") String keyword, HttpServletRequest request, Model model) {
        Locale locale = RequestContextUtils.getLocale(request);
        List<Metal> metals = metalService.searchMetal(keyword, locale);
        model.addAttribute("metals", metals);
        return "metal/metal-list";
    }
}
