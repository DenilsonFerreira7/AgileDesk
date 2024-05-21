package com.laudoStratus.demo.UploadImg;

import com.laudoStratus.demo.validacao.FileStorageVal.FileStorageValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@EnableConfigurationProperties(FileStorageProperties.class)
public class FileStorageService {

    private final Path fileStorageLocation;
    private final FileStorageValidation fileStorageValidation;
    private final long maxSize;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, FileStorageValidation fileStorageValidation) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.fileStorageValidation = fileStorageValidation;
        this.maxSize = fileStorageProperties.getMaxSize();
        fileStorageValidation.createDirectoryIfNotExists(this.fileStorageLocation); // Corrigido para criar o diretório, se não existir
    }

    public String storeFile(MultipartFile file) {
        String fileName = fileStorageValidation.validateFileName(file);
        fileStorageValidation.validateFileSize(file, maxSize);
        String filePath = fileStorageLocation.resolve(fileName).normalize().toString();
        fileStorageValidation.saveFile(file, filePath);
        String relativePath = "/imgtec/" + fileName; // Caminho relativo ao diretório raiz do aplicativo
        return relativePath;
    }

    public Resource loadFileAsResource(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        return fileStorageValidation.loadResource(fileName, filePath);
    }
}