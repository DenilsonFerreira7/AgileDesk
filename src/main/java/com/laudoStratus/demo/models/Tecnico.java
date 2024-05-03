package com.laudoStratus.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tecnicoId;

    private String nomeTecnico;

    private String cargoTecnico;

    private String email;

    private String telefone;

    private String fotoPerfil;

    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();
}
