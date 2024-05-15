package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.UploadImg.FileStorageService;
import com.laudoStratus.demo.mapper.TecnicoMapper;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;
    private final FileStorageService fileStorageService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Tecnico> cadastrarTecnico(@RequestParam("nomeTecnico") String nomeTecnico,
                                                    @RequestParam("cargoTecnico") String cargoTecnico,
                                                    @RequestParam("email") String email,
                                                    @RequestParam("telefone") String telefone,
                                                    @RequestParam("fotoPerfil") MultipartFile fotoPerfil) throws IOException {
        // Chama o serviço para cadastrar o técnico, passando o objeto MultipartFile fotoPerfil
        Tecnico novoTecnico = tecnicoService.cadastrarTecnico(nomeTecnico, cargoTecnico, email, telefone, fotoPerfil);

        // Retorna a resposta com o técnico recém-criado e o status HTTP CREATED
        return new ResponseEntity<>(novoTecnico, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TecnicoResponse>> getAllTecnicos() {
        List<TecnicoResponse> tecnicos = tecnicoService.getAllTecnicos();
        return new ResponseEntity<>(tecnicos, HttpStatus.OK);
    }

    @PostMapping("/{tecnicoId}/foto")
    public ResponseEntity<String> uploadFotoPerfil(@PathVariable Long tecnicoId, @RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        if (fileName != null) {
            // Atualize o caminho da imagem do perfil do técnico no banco de dados
            tecnicoService.updateFotoPerfil(tecnicoId, fileName);
            return ResponseEntity.ok().body("Foto do perfil do técnico foi atualizada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao fazer upload da foto do perfil do técnico.");
        }
    }

    @GetMapping("/foto/{fileName:.+}")
    public ResponseEntity<Resource> getFotoPerfil(@PathVariable String fileName) {
        // Diretório onde as fotos estão armazenadas
        Path file = Paths.get("src/main/webapp/imgtec").resolve(fileName);

        // Se o arquivo existir, carregue-o como um recurso
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se o recurso for encontrado, retorne-o
        if (resource != null && resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            // Se o recurso não for encontrado, retorne um erro 404
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{tecnicoId}/update")
    public ResponseEntity<TecnicoResponse> updateTecnico(@PathVariable Long tecnicoId,
                                                         @RequestParam("nomeTecnico") String nomeTecnico,
                                                         @RequestParam("cargoTecnico") String cargoTecnico,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("telefone") String telefone,
                                                         @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {
        try {
            // Chama o serviço para atualizar o técnico, passando todos os parâmetros
            Tecnico tecnicoAtualizado = tecnicoService.updateTecnico(tecnicoId, nomeTecnico, cargoTecnico, email, telefone, fotoPerfil);

            // Mapeia o objeto Tecnico atualizado para um objeto TecnicoResponse
            TecnicoResponse tecnicoResponse = TecnicoMapper.mapTecnicoToTecnicoResponse(tecnicoAtualizado);

            // Retorna a resposta com o técnico atualizado e o status HTTP OK
            return new ResponseEntity<>(tecnicoResponse, HttpStatus.OK);
        } catch (IOException e) {
            // Em caso de falha na atualização, retorne um erro 500
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}