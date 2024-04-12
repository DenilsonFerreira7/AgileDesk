package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Equipamento;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
}
=======
import com.laudoStratus.demo.models.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
    List<Equipamento> findAll();
//    @Query("SELECT te.nomeEquipamento, COUNT(e) FROM TipoEquipamento te LEFT JOIN Equipamento e ON te.tipoEquipamentoId = e.tipoEquipamento.tipoEquipamentoId GROUP BY te.nomeEquipamento, te.tipoEquipamentoId")
//    List<Object[]> findTotalByTipoEquipamento();

    Long countByTipoEquipamento(TipoEquipamento tipoEquipamento);

}
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
