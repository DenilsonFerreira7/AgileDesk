package com.laudoStratus.demo.service;

<<<<<<< HEAD
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
=======
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

>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
@RequiredArgsConstructor
@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
<<<<<<< HEAD
=======
    private final TipoEquipamentoService tipoEquipamentoService;
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7

    public Equipamento cadastrarEquipamento(Equipamento equipamento, Empresa empresa) {
        equipamento.setEmpresa(empresa);
        return equipamentoRepository.save(equipamento);
    }
<<<<<<< HEAD
=======

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
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
}
