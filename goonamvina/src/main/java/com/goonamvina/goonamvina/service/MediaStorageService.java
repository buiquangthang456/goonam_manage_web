package com.goonamvina.goonamvina.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaStorageService {

    private final String uploadPath = System.getProperty("user.dir") + "/uploads/images/";

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        try {
            // Kiểm tra và tạo thư mục nếu chưa tồn tại
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Lưu file vào thư mục
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadPath + fileName);
            Files.write(path, file.getBytes());

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}