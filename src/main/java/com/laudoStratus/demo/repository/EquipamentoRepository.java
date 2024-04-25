package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Equipamento;

import com.laudoStratus.demo.models.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {


    List<Equipamento> findAll();
//    @Query("SELECT te.nomeEquipamento, COUNT(e) FROM TipoEquipamento te LEFT JOIN Equipamento e ON te.tipoEquipamentoId = e.tipoEquipamento.tipoEquipamentoId GROUP BY te.nomeEquipamento, te.tipoEquipamentoId")
//    List<Object[]> findTotalByTipoEquipamento();

    Long countByTipoEquipamento(TipoEquipamento tipoEquipamento);

}

