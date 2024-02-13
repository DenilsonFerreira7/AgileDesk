package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @PostMapping(value = "/cadastrar",produces = "application/json")
    public ResponseEntity<Tecnico> CadastrarTecnico (@RequestBody Tecnico tecnico) {
        Tecnico tecnicoSalvo = tecnicoService.Cadastrar(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoSalvo);
    }
}