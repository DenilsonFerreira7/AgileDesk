package com.laudoStratus.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "TipoEquipamento")
public class TipoEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoEquipamentoId;

    @Column(name = "nome")
    private String nomeEquipamento;
}
