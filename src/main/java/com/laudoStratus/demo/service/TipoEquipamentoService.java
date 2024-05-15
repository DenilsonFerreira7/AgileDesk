package com.laudoStratus.demo.service;

import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.repository.TipoEquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipoEquipamentoService {

    private final TipoEquipamentoRepository tipoEquipamentoRepository;

    public TipoEquipamento CadastrarTipoEquipamento(TipoEquipamento tipoEquipamento) {
        return tipoEquipamentoRepository.save(tipoEquipamento);
    }

    public TipoEquipamento findById(Long id) {
        return tipoEquipamentoRepository.findById(id)
                .orElseThrow(() -> MessageNotFoundException.EquipamentoNaoEncontrado(id));
    }

    public List<TipoEquipamento> TipoEquipamentoTodos() {
        return tipoEquipamentoRepository.findAll();
    }

}