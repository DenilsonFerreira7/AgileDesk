package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public Tecnico Cadastrar (Tecnico tecnico){
        return tecnicoRepository.save(tecnico);
    }
    public List<Tecnico> getAllTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Tecnico obterTecnicoPorId(Long id) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        return tecnicoOptional.orElse(null);
    }
    public Tecnico findByNome(String nome) {
        return tecnicoRepository.findByNomeTecnico(nome);
    }

}
