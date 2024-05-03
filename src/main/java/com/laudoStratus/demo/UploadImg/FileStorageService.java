package com.laudoStratus.demo.UploadImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements ServletContextAware {

    private final Path fileStorageLocation;
    private ServletContext servletContext;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .resolve("imgtec") // Adiciona o subdiretório "imgtec"
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Falha ao criar o diretório de armazenamento de arquivos", ex);
        }
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String storeFile(MultipartFile file) throws IOException {
        // Construir o caminho absoluto para salvar o arquivo
        String filePath = fileStorageLocation.resolve(file.getOriginalFilename()).toAbsolutePath().toString();

        // Criar o arquivo no diretório de armazenamento e copiar o conteúdo do arquivo recebido para ele
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        // Retornar o caminho relativo do arquivo dentro do diretório de armazenamento
        return getRelativePath(filePath);
    }

    private String getRelativePath(String absolutePath) {
        // Converte o caminho absoluto para URI
        URI uri = new File(absolutePath).toURI();
        // Obtém o caminho relativo da URI em relação ao diretório de armazenamento
        String relativePath = fileStorageLocation.relativize(Paths.get(uri)).toString();
        // Retorna o caminho relativo
        return "/imgtec/" + relativePath;
    }
}
