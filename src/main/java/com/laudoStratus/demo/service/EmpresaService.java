package com.laudoStratus.demo.service;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.validacao.empresaVal.EmpresaValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaValidationService empresaValidationService;

    public Empresa Cadastrar (Empresa empresa) {
        empresaValidationService.validateCadastro(empresa);
        return empresaRepository.save(empresa);
    }

    public Empresa obterEmpresaPorId(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        empresaValidationService.validateObterEmpresaPorId(id);
        return empresaOptional.orElse(null);
    }


    public List <Empresa> findAll (){
        return empresaRepository.findAll();
    }

    public Empresa findByNome (Empresa empresa) {
        empresaValidationService.validateCadastro(empresa);
        return empresaRepository.findByNomeEmpresa(empresa.getNomeEmpresa());
    }

    public List<Equipamento> findEquipamentosByIds(List<Long> idsEquipamentos) {
        return empresaRepository.findEquipamentosByIds(idsEquipamentos);
    }

}

