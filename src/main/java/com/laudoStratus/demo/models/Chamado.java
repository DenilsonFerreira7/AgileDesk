package com.laudoStratus.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "chamado")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChamado;

    private String titulo;

    private String status;

    private String operadora;

    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDateTime;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    @JsonBackReference
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "chamado")
    @JsonManagedReference
    private List<DescricaoAtualizacao> descriptionUpdates;
}
