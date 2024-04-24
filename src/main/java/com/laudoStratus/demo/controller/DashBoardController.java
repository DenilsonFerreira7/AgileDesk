package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.ContagemEquipamentoPorTipo;
import com.laudoStratus.demo.DTO.EquipamentoSetorDTO;
import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.TipoEquipamentoRepository;
import com.laudoStratus.demo.service.ContagemEquipamentoService;
import com.laudoStratus.demo.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dash")
public class DashBoardController {


    private final TipoEquipamentoRepository tipoEquipamentoRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final EquipamentoService equipamentoService;

    @GetMapping("/quantidadePorTipo")
    public Map<String, Long> getQuantidadePorTipoEquipamento() {
        List<TipoEquipamento> tiposEquipamento = tipoEquipamentoRepository.findAll();
        Map<String, Long> quantidadePorTipo = new HashMap<>();

        for (TipoEquipamento tipoEquipamento : tiposEquipamento) {
            Long quantidade = equipamentoRepository.countByTipoEquipamento(tipoEquipamento);
            quantidadePorTipo.put(tipoEquipamento.getNomeEquipamento(), quantidade);
        }

        return quantidadePorTipo;
    }

    @GetMapping("/contagemPorSetor")
    public List<EquipamentoSetorDTO> contarEquipamentosPorSetor() {
        return equipamentoService.contarEquipamentosPorSetor();
    }

}