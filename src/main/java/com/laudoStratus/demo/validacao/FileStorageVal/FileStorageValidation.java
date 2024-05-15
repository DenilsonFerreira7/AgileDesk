package com.laudoStratus.demo.validacao.FileStorageVal;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileStorageValidation {

    public String validateFileName(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new RuntimeException("Invalid file name: " + fileName);
        }
        return fileName;
    }

    public void validateFileSize(MultipartFile file, long maxSize) {
        if (file.getSize() > maxSize) {
            throw new RuntimeException("File size exceeds the allowed limit: " + file.getSize());
        }
    }

    public void createDirectoryIfNotExists(Path fileStorageLocation) {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to create directory", ex);
        }
    }

    public void saveFile(MultipartFile file, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), ex);
        }
    }

    public Resource loadResource(String fileName, Path filePath) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + fileName);
            }
            return resource;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error loading file " + fileName, ex);
        }
    }
}
