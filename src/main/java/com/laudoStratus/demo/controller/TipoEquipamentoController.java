package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.service.TipoEquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/TipoEquipamento")
public class TipoEquipamentoController {

    private final TipoEquipamentoService tipoEquipamentoService;

    @PostMapping(value = "/cadastrar",produces = "application/json")
    public ResponseEntity<TipoEquipamento> cadastrarTipoEquipamento (@RequestBody TipoEquipamento tipoEquipamento) {
        TipoEquipamento novoTipoEquipamento = tipoEquipamentoService.CadastrarTipoEquipamento(tipoEquipamento);
        return new ResponseEntity<>(novoTipoEquipamento, HttpStatus.CREATED);
    }

    @GetMapping(value = "/todas", produces = "application/json")
    public ResponseEntity<List<TipoEquipamento>> findAll() {
        List<TipoEquipamento> equipamentos = tipoEquipamentoService.TipoEquipamentoTodos();
        return new ResponseEntity<>(equipamentos, HttpStatus.OK);
    }
}
