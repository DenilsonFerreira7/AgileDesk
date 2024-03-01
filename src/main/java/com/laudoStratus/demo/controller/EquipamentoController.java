package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.service.EmpresaService;
import com.laudoStratus.demo.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/empresas/{empresaId}/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;
    private final EmpresaService empresaService;

    @PostMapping("/cadastrar")
    public Equipamento cadastrarEquipamento(
            @PathVariable Long empresaId,
            @RequestParam Long tipoEquipamentoId,
            @RequestBody Equipamento equipamento) {
        Empresa empresa = empresaService.obterEmpresaPorId(empresaId);
        return equipamentoService.cadastrarEquipamentoComTipo(equipamento, empresa, tipoEquipamentoId);
    }

    @GetMapping("/consultar")
    public List<Equipamento> consultarEquipamentosDaEmpresa(@PathVariable Long empresaId) {
        Empresa empresa = empresaService.obterEmpresaPorId(empresaId);
        return equipamentoService.consultarEquipamentosDaEmpresa(empresa);
    }
}