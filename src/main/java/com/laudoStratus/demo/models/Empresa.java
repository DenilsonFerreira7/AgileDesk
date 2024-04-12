package com.laudoStratus.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity

<<<<<<< HEAD
@Table(name = "Empresa")
=======
@Table(name = "empresa")
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7
public class Empresa implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id;
=======
    private Long empresaId;
>>>>>>> b6c0b60e0115e79fe73b3d62f3f8435add07bea7

    @Column(name = "nome")
    private String nomeEmpresa;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Equipamento> equipamentos;

}