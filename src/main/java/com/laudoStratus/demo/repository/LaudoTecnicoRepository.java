package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.LaudoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaudoTecnicoRepository extends JpaRepository <LaudoTecnico,Long> {
    List<LaudoTecnico> findByEmpresaNomeEmpresaIgnoreCase(String nomeEmpresa);

}
