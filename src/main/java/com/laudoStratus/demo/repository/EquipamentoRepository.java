package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
}
