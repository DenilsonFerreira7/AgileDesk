package com.laudoStratus.demo.service;
import com.laudoStratus.demo.models.Empresa;
<<<<<<< HEAD
=======
import com.laudoStratus.demo.models.Equipamento;
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
import com.laudoStratus.demo.repository.EmpresaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
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
<<<<<<< HEAD
=======

    public List <Empresa> findAll (){
        return empresaRepository.findAll();
    }

    public Empresa findByNome(String nome) {
        return empresaRepository.findByNomeEmpresa(nome);
    }

    public List<Equipamento> findEquipamentosByIds(List<Long> idsEquipamentos) {
        return empresaRepository.findEquipamentosByIds(idsEquipamentos);
    }
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
}

