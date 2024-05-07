package com.laudoStratus.demo.validacao.empresaVal;

import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
@RequiredArgsConstructor
public class EmpresaValidationService {

    private final EmpresaRepository empresaRepository;

    public boolean validateCadastro(Empresa empresa) {
        if (empresa == null) {
            throw  MessageNotFoundException.EmpresaIdNull(null);
        }
        if (empresa.getNomeEmpresa() == null || empresa.getNomeEmpresa().isEmpty()) {
            throw MessageNotFoundException.NomeEmpresaObrigatorio(empresa.getNomeEmpresa());
        }
        if (empresaRepository.findByNomeEmpresa(empresa.getNomeEmpresa()) != null) {
            throw MessageNotFoundException.NomeEmpresaNaoEncontrado(empresa.getNomeEmpresa());
        }
        return true;
    }

    public boolean validateObterEmpresaPorId(Long id) {
        if (id == null) {
            throw MessageNotFoundException.EmpresaIdNull(id);
        }
        if (!empresaRepository.existsById(id)) {
            throw MessageNotFoundException.EmpresaNaoEncontrada(id);
        }
        return true;
    }

}