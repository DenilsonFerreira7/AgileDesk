package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.UploadImg.FileStorageService;
import com.laudoStratus.demo.mapper.TecnicoMapper;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.TecnicoRepository;
import com.laudoStratus.demo.validacao.tecnicoVal.TecnicoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final FileStorageService fileStorageService;
    private final TecnicoValidation tecnicoValidation;

    public Tecnico cadastrarTecnico
            (String nomeTecnico, String cargoTecnico, String email, String telefone, MultipartFile fotoPerfil) throws IOException {
        String fotoPerfilPath = fileStorageService.storeFile(fotoPerfil); // Salva a imagem e obtém o caminho completo
        tecnicoValidation.validateFotoPerfilPath(fotoPerfilPath);
        return saveTecnico(nomeTecnico, cargoTecnico, email, telefone, fotoPerfilPath);
    }

    private Tecnico saveTecnico(String nomeTecnico, String cargoTecnico, String email, String telefone, String fotoPerfilPath) {
        Tecnico novoTecnico = new Tecnico();
        novoTecnico.setNomeTecnico(nomeTecnico);
        novoTecnico.setCargoTecnico(cargoTecnico);
        novoTecnico.setEmail(email);
        novoTecnico.setTelefone(telefone);
        novoTecnico.setFotoPerfil(fotoPerfilPath);
        return tecnicoRepository.save(novoTecnico);
    }

    public List<TecnicoResponse> getAllTecnicos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();
        return tecnicos.stream()
                .map(TecnicoMapper::mapTecnicoToTecnicoResponse)
                .collect(Collectors.toList());
    }

    public void updateFotoPerfil(Long tecnicoId, String fotoPerfil) {
        tecnicoValidation.updateFotoPerfil(tecnicoId, fotoPerfil, tecnicoRepository);
    }

    public Tecnico obterTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id).orElse(null);
    }

    public Tecnico findByNome(String nome) {
        return tecnicoRepository.findByNomeTecnico(nome);
    }

    public Tecnico updateTecnico
            (Long tecnicoId, String nomeTecnico, String cargoTecnico, String email, String telefone, MultipartFile novaFotoPerfil) throws IOException {
        return tecnicoValidation.validateAndUpdateTecnico(tecnicoId, nomeTecnico, cargoTecnico, email, telefone, novaFotoPerfil, tecnicoRepository, fileStorageService);
    }
}
