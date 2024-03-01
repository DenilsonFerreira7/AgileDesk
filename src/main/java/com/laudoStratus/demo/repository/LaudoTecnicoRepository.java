package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.LaudoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LaudoTecnicoRepository extends JpaRepository <LaudoTecnico,Long> {
    List<LaudoTecnico> findByEmpresaNomeEmpresaIgnoreCase(String nomeEmpresa);

}
