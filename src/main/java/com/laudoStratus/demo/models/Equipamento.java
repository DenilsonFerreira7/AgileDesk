package com.laudoStratus.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "equipamento")
public class Equipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nomeEquipamento;

    @Column(name = "setor")
    private String setor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "acessoRemoto")
    private String acessoRemoto;

    @Column(name = "senhaRemoto")
    private String senhaRemoto;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "tipoEquipamento_id")
    private TipoEquipamento tipoEquipamento;
}
