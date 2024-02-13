package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping (value = "/cadastrar",produces = "application/json")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        Empresa savedEmpresa = empresaService.Cadastrar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpresa);
    }
}
