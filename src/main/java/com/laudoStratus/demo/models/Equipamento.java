package com.laudoStratus.demo.models;
<<<<<<< HEAD
=======

>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
<<<<<<< HEAD
=======

>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name = "Equipamento")
=======
@Table(name = "equipamento")
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
public class Equipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nomeEquipamento;

<<<<<<< HEAD
    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
=======
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
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
