package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.UploadImg.FileStorageService;
import com.laudoStratus.demo.mapper.TecnicoMapper;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final FileStorageService fileStorageService;

    public Tecnico cadastrarTecnico(String nomeTecnico, String cargoTecnico, String email, String telefone, MultipartFile fotoPerfil) throws IOException {
        Tecnico novoTecnico = new Tecnico();
        novoTecnico.setNomeTecnico(nomeTecnico);
        novoTecnico.setCargoTecnico(cargoTecnico);
        novoTecnico.setEmail(email);
        novoTecnico.setTelefone(telefone);
        // Salvar a foto do perfil em algum lugar e definir o caminho no objeto Tecnico
        String fotoPerfilPath = salvarFotoPerfil(fotoPerfil);
        novoTecnico.setFotoPerfil(fotoPerfilPath);
        return tecnicoRepository.save(novoTecnico);
    }

    private String salvarFotoPerfil(MultipartFile fotoPerfil) throws IOException {
        // Use o serviço FileStorageService para salvar a foto
        String fotoPerfilPath = fileStorageService.storeFile(fotoPerfil);
        return fotoPerfilPath;
    }


    public List<TecnicoResponse> getAllTecnicos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();
        return tecnicos.stream()
                .map(TecnicoMapper::mapTecnicoToTecnicoResponse)
                .collect(Collectors.toList());
    }

    public void updateFotoPerfil(Long tecnicoId, String fotoPerfil) {
        Optional<Tecnico> optionalTecnico = tecnicoRepository.findById(tecnicoId);
        if (optionalTecnico.isPresent()) {
            Tecnico tecnico = optionalTecnico.get();
            tecnico.setFotoPerfil(fotoPerfil);
            tecnicoRepository.save(tecnico);
        } else {
            // Lidar com o cenário onde o técnico com o ID fornecido não foi encontrado
        }
    }

    public Tecnico obterTecnicoPorId(Long id) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        return tecnicoOptional.orElse(null);
    }

    public Tecnico findByNome(String nome) {
        return tecnicoRepository.findByNomeTecnico(nome);
    }
}
