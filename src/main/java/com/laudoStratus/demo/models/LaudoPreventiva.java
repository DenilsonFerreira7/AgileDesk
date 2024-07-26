package com.laudoStratus.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "laudo_preventiva")
public class LaudoPreventiva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLaudoPrev;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "laudo_preventiva_equipamento",
            joinColumns = @JoinColumn(name = "laudo_preventiva_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
    private List<Equipamento> equipamentos;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    private String descricao;

    private LocalDate dataCriacao;
}
