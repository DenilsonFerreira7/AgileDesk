package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository <Empresa,Long> {
    Empresa findByNomeEmpresa(String nome);

    @Query("SELECT e FROM Equipamento e WHERE e.empresa.id IN :idsEquipamentos")
    List<Equipamento> findEquipamentosByIds(List<Long> idsEquipamentos);
}

