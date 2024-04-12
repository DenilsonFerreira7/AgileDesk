package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository <Tecnico,Long> {
    Tecnico findByNomeTecnico(String nome);

}
