package com.laudoStratus.demo.service;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.repository.EmpresaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public Empresa Cadastrar (Empresa empresa) {
        return empresaRepository.save(empresa);
    }
    public Empresa obterEmpresaPorId(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.orElse(null);
    }
}

