package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public Tecnico Cadastrar (Tecnico tecnico){
        return tecnicoRepository.save(tecnico);
    }
}
