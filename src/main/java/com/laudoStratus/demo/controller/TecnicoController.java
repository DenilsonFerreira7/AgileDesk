package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.UploadImg.FileStorageService;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        Tecnico novoTecnico = tecnicoService.cadastrarTecnico(nomeTecnico, cargoTecnico, email, telefone, fotoPerfil);
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
}
