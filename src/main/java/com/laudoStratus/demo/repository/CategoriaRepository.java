package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categorias,Long> {
}
