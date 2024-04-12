package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
<<<<<<< HEAD
import com.laudoStratus.demo.repository.EmpresaRepository;
=======
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
import com.laudoStratus.demo.service.EmpresaService;
import com.laudoStratus.demo.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
<<<<<<< HEAD
@RequestMapping("/empresas/{empresaId}/equipamentos")
=======
@RequestMapping("/empresa/{empresaId}/equipamento")
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
public class EquipamentoController {

    private final EquipamentoService equipamentoService;
    private final EmpresaService empresaService;

<<<<<<< HEAD
    @PostMapping("/cadastrar")
    public Equipamento cadastrarEquipamento(@PathVariable Long empresaId, @RequestBody Equipamento equipamento) {
        Empresa empresa = empresaService.obterEmpresaPorId(empresaId);
        return equipamentoService.cadastrarEquipamento(equipamento, empresa);
=======

    @PostMapping("/cadastrar")
    public Equipamento cadastrarEquipamento(
            @PathVariable Long empresaId,
            @RequestParam Long tipoEquipamentoId,
            @RequestBody Equipamento equipamento) {
        Empresa empresa = empresaService.obterEmpresaPorId(empresaId);
        return equipamentoService.cadastrarEquipamentoComTipo(equipamento, empresa, tipoEquipamentoId);
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
    }

    @GetMapping("/consultar")
    public List<Equipamento> consultarEquipamentosDaEmpresa(@PathVariable Long empresaId) {
        Empresa empresa = empresaService.obterEmpresaPorId(empresaId);
<<<<<<< HEAD
        return empresa.getEquipamentos();
=======
        return equipamentoService.consultarEquipamentosDaEmpresa(empresa);
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
    }
}