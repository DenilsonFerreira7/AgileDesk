package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {


}
