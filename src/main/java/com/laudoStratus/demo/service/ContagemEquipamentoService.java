package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.ContagemEquipamentoPorTipo;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContagemEquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public List<ContagemEquipamentoPorTipo> contarEquipamentosPorTipo() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();

        Map<String, Long> contagemPorTipo = equipamentos.stream()
                .collect(Collectors.groupingBy(Equipamento::getDescricao, Collectors.counting()));

        return contagemPorTipo.entrySet().stream()
                .map(entry -> new ContagemEquipamentoPorTipo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
