package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.LaudoPreventiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaudoPreventivaRepository extends JpaRepository <LaudoPreventiva, Long> {
}
