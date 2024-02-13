package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.LaudoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaudoTecnicoRepository extends JpaRepository <LaudoTecnico,Long> {
}
