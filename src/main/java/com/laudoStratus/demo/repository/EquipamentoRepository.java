package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
    List<Equipamento> findAll();

}
