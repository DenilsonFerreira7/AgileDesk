package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @PostMapping(value = "/cadastrar",produces = "application/json")
    public ResponseEntity<Tecnico> cadastrarTecnico(@RequestBody Tecnico tecnico) {
        Tecnico novoTecnico = tecnicoService.Cadastrar(tecnico);
        return new ResponseEntity<>(novoTecnico, HttpStatus.CREATED);
    }
    @GetMapping("/todos")
    public ResponseEntity<List<TecnicoResponse>> getAllTecnicos() {
        List<TecnicoResponse> tecnicos = tecnicoService.getAllTecnicos();
        return new ResponseEntity<>(tecnicos, HttpStatus.OK);
    }

}