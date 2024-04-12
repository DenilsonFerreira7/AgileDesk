package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento,Long> {

}
