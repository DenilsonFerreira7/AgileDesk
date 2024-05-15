package com.laudoStratus.demo.validacao.tecnicoVal;

import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.UploadImg.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

import com.laudoStratus.demo.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class TecnicoValidation {

    public void validateFotoPerfilPath(String fotoPerfilPath) {
        if (fotoPerfilPath == null) {
            throw new RuntimeException("Falha ao salvar a imagem do perfil do técnico.");
        }
    }

    public void validateNovaFotoPerfilPath(String novaFotoPath) {
        if (novaFotoPath == null) {
            throw new RuntimeException("Falha ao salvar a nova foto de perfil.");
        }
    }

    public void updateFotoPerfil(Long tecnicoId, String fotoPerfil, TecnicoRepository tecnicoRepository) {
        Optional<Tecnico> optionalTecnico = tecnicoRepository.findById(tecnicoId);
        if (optionalTecnico.isPresent()) {
            Tecnico tecnico = optionalTecnico.get();
            tecnico.setFotoPerfil(fotoPerfil);
            tecnicoRepository.save(tecnico);
        } else {
            throw new RuntimeException("Técnico com o ID fornecido não encontrado.");
        }
    }

    public Tecnico validateAndUpdateTecnico(Long tecnicoId, String nomeTecnico, String cargoTecnico, String email, String telefone, MultipartFile novaFotoPerfil, TecnicoRepository tecnicoRepository, FileStorageService fileStorageService) throws IOException {
        Optional<Tecnico> optionalTecnico = tecnicoRepository.findById(tecnicoId);
        if (optionalTecnico.isPresent()) {
            Tecnico tecnico = optionalTecnico.get();

            tecnico.setNomeTecnico(nomeTecnico);
            tecnico.setCargoTecnico(cargoTecnico);
            tecnico.setEmail(email);
            tecnico.setTelefone(telefone);

            if (novaFotoPerfil != null) {
                String novaFotoPath = fileStorageService.storeFile(novaFotoPerfil);
                validateNovaFotoPerfilPath(novaFotoPath);
                String novaFotoPerfilCompleta = "/imgtec/" + novaFotoPath;
                tecnico.setFotoPerfil(novaFotoPerfilCompleta);
            }

            return tecnicoRepository.save(tecnico);
        } else {
            throw new RuntimeException("Técnico com o ID fornecido não encontrado.");
        }
    }
}
