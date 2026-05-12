package com.goonamvina.goonamvina.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class MediaAdminController {
    @GetMapping("/media")
    public String viewMedia(Model model) {
        File folder = new File(System.getProperty("user.dir") + "/uploads/images/");
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    fileNames.add("/uploads/images/" + file.getName());
                }
            }
        }

        model.addAttribute("files", fileNames);
        return "admin/media/media";
    }
}