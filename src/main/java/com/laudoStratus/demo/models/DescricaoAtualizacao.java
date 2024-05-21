package com.laudoStratus.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "descricao")
public class DescricaoAtualizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescr;

    private String descricao;

    private String atualizadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

    @ManyToOne
    @JoinColumn(name = "chamado_id")
    @JsonBackReference
    private Chamado chamado;
}
