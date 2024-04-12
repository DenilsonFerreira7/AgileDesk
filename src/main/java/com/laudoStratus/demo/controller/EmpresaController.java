package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7

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
<<<<<<< HEAD
=======
    @GetMapping (value = "/todas")
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = empresaService.findAll();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
}
