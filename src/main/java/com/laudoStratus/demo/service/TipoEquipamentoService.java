package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.repository.TipoEquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TipoEquipamentoService {

    private final TipoEquipamentoRepository tipoEquipamentoRepository;

    public TipoEquipamento CadastrarTipoEquipamento(TipoEquipamento tipoEquipamento) {
        return tipoEquipamentoRepository.save(tipoEquipamento);
    }

    public TipoEquipamento findById(Long id) {
        return tipoEquipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de equipamento não encontrado"));
    }

    public List<TipoEquipamento> TipoEquipamentoTodos() {
        return tipoEquipamentoRepository.findAll();
    }

}