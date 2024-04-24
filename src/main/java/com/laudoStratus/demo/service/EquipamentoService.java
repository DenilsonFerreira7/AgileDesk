package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.EquipamentoSetorDTO;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.service.TipoEquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final TipoEquipamentoService tipoEquipamentoService;

    public Equipamento cadastrarEquipamento(Equipamento equipamento, Empresa empresa) {
        equipamento.setEmpresa(empresa);
        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> consultarEquipamentosDaEmpresa(Empresa empresa) {
        return empresa.getEquipamentos();
    }

    public Equipamento cadastrarEquipamentoComTipo(Equipamento equipamento, Empresa empresa, Long tipoEquipamentoId) {
        TipoEquipamento tipoEquipamento = tipoEquipamentoService.findById(tipoEquipamentoId);
        equipamento.setTipoEquipamento(tipoEquipamento);
        equipamento.setEmpresa(empresa);
        return equipamentoRepository.save(equipamento);
    }
    public Long findTotalByTipoEquipamento(TipoEquipamento tipoEquipamento) {
        return equipamentoRepository.countByTipoEquipamento(tipoEquipamento);
    }

    public List<EquipamentoSetorDTO> contarEquipamentosPorSetor() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();

        Map<String, Long> contadorPorSetor = equipamentos.stream()
                .collect(Collectors.groupingBy(Equipamento::getSetor, Collectors.counting()));

        return contadorPorSetor.entrySet().stream()
                .map(entry -> new EquipamentoSetorDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
