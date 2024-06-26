package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
}
