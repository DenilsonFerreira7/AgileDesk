package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public Equipamento cadastrarEquipamento(Equipamento equipamento, Empresa empresa) {
        equipamento.setEmpresa(empresa);
        return equipamentoRepository.save(equipamento);
    }
}
