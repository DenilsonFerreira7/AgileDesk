package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Categorias;
import com.laudoStratus.demo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categorias criarCategoria(Categorias categorias) {
        categoriaRepository.save(categorias);
        return categorias;
    }

    public List findAllCategoria (){
        return categoriaRepository.findAll();
    }
}
