package com.laudoStratus.demo.UploadImg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageProperties {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}