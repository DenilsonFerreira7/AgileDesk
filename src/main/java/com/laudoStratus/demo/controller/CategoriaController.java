package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.ChamadoDTO;
import com.laudoStratus.demo.models.Categorias;
import com.laudoStratus.demo.models.Chamado;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("criar")
    public ResponseEntity<String> criarCategoria(@RequestBody Categorias categorias) {
        Categorias novaCategoria = categoriaService.criarCategoria(categorias);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
        }

    @GetMapping("listarCategoria")
    public ResponseEntity<List<Categorias>> findAll() {
        List<Categorias> categoriasList = categoriaService.findAllCategoria();
        return new ResponseEntity<>(categoriasList, HttpStatus.OK);
    }
}
