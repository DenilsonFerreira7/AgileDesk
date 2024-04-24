package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.LaudoPreventiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaudoPreventivaRepository extends JpaRepository <LaudoPreventiva, Long> {

    @Query("SELECT COUNT(e), te.nomeEquipamento, e.setor " +
            "FROM LaudoPreventiva lp " +
            "JOIN lp.equipamentos e " +
            "JOIN e.tipoEquipamento te " +
            "WHERE lp.id = :laudoPreventivaId " +
            "GROUP BY te.nomeEquipamento, e.setor")
    List<Object[]> countEquipamentosByTipoAndSetor(@Param("laudoPreventivaId") Long laudoPreventivaId);
}
