package com.api.stormbook.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageUtils {
    private final String UPLOAD_DIR = "uploads/";
    //upload 1 anh
    public String saveImage(MultipartFile file) throws IOException {
        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // Lưu file vào thư mục
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        file.transferTo(filePath);

        return "/uploads/" + fileName; // Trả về đường dẫn của ảnh
    }

    //upload nhieu anh
    public List<String> saveImages(List<MultipartFile> files) throws IOException {
        System.out.println("Received images: " + files);
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String filePath = saveImage(file);
            filePaths.add(filePath);
        }
        return filePaths;
    }

}
